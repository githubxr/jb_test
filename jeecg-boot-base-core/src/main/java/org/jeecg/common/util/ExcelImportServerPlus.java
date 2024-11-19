package org.jeecg.common.util;

//package org.jeecgframework.poi.excel.imports;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.params.ExcelCollectionParams;
import org.jeecgframework.poi.excel.entity.params.ExcelImportEntity;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.entity.result.ExcelVerifyHanlderResult;
import org.jeecgframework.poi.excel.imports.CellValueServer;
import org.jeecgframework.poi.excel.imports.base.ImportBaseService;
import org.jeecgframework.poi.excel.imports.base.ImportFileServiceI;
import org.jeecgframework.poi.excel.imports.verifys.VerifyHandlerServer;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
import org.jeecgframework.poi.exception.excel.enums.ExcelImportEnum;
import org.jeecgframework.poi.util.ExcelUtil;
import org.jeecgframework.poi.util.PoiPublicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelImportServerPlus extends ImportBaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportServerPlus.class);
    private CellValueServer cellValueServer = new CellValueServer();
    private VerifyHandlerServer verifyHandlerServer = new VerifyHandlerServer();
    private boolean verfiyFail = false;
    private static final Pattern lettersAndNumbersPattern = Pattern.compile("^[a-zA-Z0-9]+$");
    private CellStyle errorCellStyle;

    public ExcelImportServerPlus() {
    }

    private void addListContinue(Object object, ExcelCollectionParams param, Row row, Map<Integer, String> titlemap, String targetId, Map<String, PictureData> pictures, ImportParams params) throws Exception {
        Collection collection = (Collection) PoiPublicUtil.getMethod(param.getName(), object.getClass()).invoke(object);
        Object entity = PoiPublicUtil.createObject(param.getType(), targetId);
        boolean isUsed = false;

        for(int i = row.getFirstCellNum(); i < row.getLastCellNum(); ++i) {
            Cell cell = row.getCell(i);
            String titleString = (String)titlemap.get(i);
            if (param.getExcelParams().containsKey(titleString)) {
                if (((ExcelImportEntity)param.getExcelParams().get(titleString)).getType() == 2) {
                    String picId = row.getRowNum() + "_" + i;
                    this.saveImage(object, picId, param.getExcelParams(), titleString, pictures, params);
                } else {
                    this.saveFieldValue(params, entity, cell, param.getExcelParams(), titleString, row);
                }

                isUsed = true;
            }
        }

        if (isUsed) {
            collection.add(entity);
        }

    }

    private String getKeyValue(Cell cell) {
        if (cell == null) {
            return null;
        } else {
            Object obj = null;
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    obj = cell.getStringCellValue();
                    break;
                case BOOLEAN:
                    obj = cell.getBooleanCellValue();
                    break;
                case NUMERIC:
                    obj = cell.getNumericCellValue();
                    break;
                case FORMULA:
                    obj = cell.getCellFormula();
            }

            return obj == null ? null : obj.toString().trim();
        }
    }

    private String getSaveUrl(ExcelImportEntity excelImportEntity, Object object) throws Exception {
        String url = "";
        if (excelImportEntity.getSaveUrl().equals("upload")) {
            if (excelImportEntity.getMethods() != null && excelImportEntity.getMethods().size() > 0) {
                object = this.getFieldBySomeMethod(excelImportEntity.getMethods(), object);
            }

            url = object.getClass().getName().split("\\.")[object.getClass().getName().split("\\.").length - 1];
            return excelImportEntity.getSaveUrl() + "/" + url.substring(0, url.lastIndexOf("Entity"));
        } else {
            return excelImportEntity.getSaveUrl();
        }
    }

    private <T> List<T> importExcel(Collection<T> result, Sheet sheet, Class<?> pojoClass, ImportParams params, Map<String, PictureData> pictures) throws Exception {
        List collection = new ArrayList();
        Map<String, ExcelImportEntity> excelParams = new HashMap();
        List<ExcelCollectionParams> excelCollection = new ArrayList();
        String targetId = null;
        if (!Map.class.equals(pojoClass)) {
            Field[] fileds = PoiPublicUtil.getClassFields(pojoClass);
            ExcelTarget etarget = (ExcelTarget)pojoClass.getAnnotation(ExcelTarget.class);
            if (etarget != null) {
                targetId = etarget.value();
            }

            this.getAllExcelField(targetId, fileds, excelParams, excelCollection, pojoClass, (List)null);
        }

        this.ignoreHeaderHandler(excelParams, params);
        Iterator<Row> rows = sheet.rowIterator();
        Map<Integer, String> titlemap = this.getTitleMap(sheet, rows, params, excelCollection);
        Set<String> keys = excelParams.keySet();
        Iterator var13 = keys.iterator();

        while(var13.hasNext()) {
            String key = (String)var13.next();
            if (key.startsWith("FIXED_")) {
                String[] arr = key.split("_");
                titlemap.put(Integer.parseInt(arr[1]), key);
            }
        }

        Set<Integer> columnIndexSet = titlemap.keySet();
        Integer maxColumnIndex = (Integer)Collections.max(columnIndexSet);
        Integer minColumnIndex = (Integer)Collections.min(columnIndexSet);
        Row row = null;

        //2024-11-11：添加startRows，以修复startRows无效的情况
        for(int j = 0; j < params.getTitleRows() + params.getHeadRows() + params.getStartRows(); ++j) {
            row = (Row)rows.next();
        }

        Object object = null;

        while(rows.hasNext() && (row == null || sheet.getLastRowNum() - row.getRowNum() > params.getLastOfInvalidRow())) {
            row = (Row)rows.next();
            Cell keyIndexCell = row.getCell(params.getKeyIndex());
            if (excelCollection.size() > 0 && StringUtils.isEmpty(this.getKeyValue(keyIndexCell)) && object != null && !Map.class.equals(pojoClass)) {
                Iterator var35 = excelCollection.iterator();

                while(var35.hasNext()) {
                    ExcelCollectionParams param = (ExcelCollectionParams)var35.next();
                    this.addListContinue(object, param, row, titlemap, targetId, pictures, params);
                }
            } else {
                object = PoiPublicUtil.createObject(pojoClass, targetId);

                try {
                    int firstCellNum = row.getFirstCellNum();
                    if (firstCellNum > minColumnIndex) {
                        firstCellNum = minColumnIndex;
                    }

                    int lastCellNum = row.getLastCellNum();
                    if (lastCellNum < maxColumnIndex + 1) {
                        lastCellNum = maxColumnIndex + 1;
                    }

                    int i = firstCellNum;

                    for(int le = lastCellNum; i < le; ++i) {
                        Cell cell = row.getCell(i);
                        String titleString = (String)titlemap.get(i);
                        if (excelParams.containsKey(titleString) || Map.class.equals(pojoClass)) {
                            String picId;
                            if (excelParams.get(titleString) != null && ((ExcelImportEntity)excelParams.get(titleString)).getType() == 2) {
                                picId = row.getRowNum() + "_" + i;
                                this.saveImage(object, picId, excelParams, titleString, pictures, params);
                            } else if (params.getImageList() != null && params.getImageList().contains(titleString)) {
                                if (pictures != null) {
                                    picId = row.getRowNum() + "_" + i;
                                    PictureData image = (PictureData)pictures.get(picId);
                                    if (image != null) {
                                        byte[] data = image.getData();
                                        params.getDataHanlder().setMapValue((Map)object, titleString, data);
                                    }
                                }
                            } else {
                                this.saveFieldValue(params, object, cell, excelParams, titleString, row);
                            }
                        }
                    }

                    Iterator var37 = excelCollection.iterator();

                    while(var37.hasNext()) {
                        ExcelCollectionParams param = (ExcelCollectionParams)var37.next();
                        this.addListContinue(object, param, row, titlemap, targetId, pictures, params);
                    }

                    if (this.isNotNullObject(pojoClass, object)) {
                        collection.add(object);
                    }
                } catch (ExcelImportException var28) {
                    if (!var28.getType().equals(ExcelImportEnum.VERIFY_ERROR)) {
                        throw new ExcelImportException(var28.getType(), var28);
                    }
                }
            }
        }

        return collection;
    }

    private boolean isNotNullObject(Class pojoClass, Object object) {
        try {
            Method method = pojoClass.getMethod("isNullObject");
            if (method != null) {
                Object flag = method.invoke(object);
                if (flag != null && Boolean.parseBoolean(flag.toString())) {
                    return false;
                }
            }
        } catch (NoSuchMethodException var5) {
            LOGGER.debug("未定义方法 isNullObject");
        } catch (IllegalAccessException var6) {
            LOGGER.warn("没有权限访问该方法 isNullObject");
        } catch (InvocationTargetException var7) {
            LOGGER.warn("方法调用失败 isNullObject");
        }

        return true;
    }

    private void ignoreHeaderHandler(Map<String, ExcelImportEntity> excelParams, ImportParams params) {
        List<String> ignoreList = new ArrayList();
        Iterator var4 = excelParams.keySet().iterator();

        while(var4.hasNext()) {
            String key = (String)var4.next();
            String temp = ((ExcelImportEntity)excelParams.get(key)).getGroupName();
            if (temp != null && temp.length() > 0) {
                ignoreList.add(temp);
            }
        }

        params.setIgnoreHeaderList(ignoreList);
    }

    private Map<Integer, String> getTitleMap(Sheet sheet, Iterator<Row> rows, ImportParams params, List<ExcelCollectionParams> excelCollection) throws Exception {
        Map<Integer, String> titlemap = new HashMap();
        Iterator<Cell> cellTitle = null;
        String collectionName = null;
        ExcelCollectionParams collectionParams = null;
        Row headRow = null;
        int headBegin = params.getTitleRows();

        for(int allRowNum = sheet.getPhysicalNumberOfRows(); headRow == null && headBegin < allRowNum; headRow = sheet.getRow(headBegin++)) {
        }

        if (headRow == null) {
            throw new Exception("不识别该文件");
        } else {
            if (ExcelUtil.isMergedRegion(sheet, headRow.getRowNum(), 0)) {
                params.setHeadRows(2);
            } else {
                params.setHeadRows(1);
            }

            cellTitle = headRow.cellIterator();

            while(cellTitle.hasNext()) {
                Cell cell = (Cell)cellTitle.next();
                String value = this.getKeyValue(cell);
                if (StringUtils.isNotEmpty(value)) {
                    titlemap.put(cell.getColumnIndex(), value);
                }
            }

            label60:
            for(int j = headBegin; j < headBegin + params.getHeadRows() - 1; ++j) {
                headRow = sheet.getRow(j);
                cellTitle = headRow.cellIterator();

                while(true) {
                    while(true) {
                        String value;
                        Cell cell;
                        do {
                            if (!cellTitle.hasNext()) {
                                continue label60;
                            }

                            cell = (Cell)cellTitle.next();
                            value = this.getKeyValue(cell);
                        } while(!StringUtils.isNotEmpty(value));

                        int columnIndex = cell.getColumnIndex();
                        if (ExcelUtil.isMergedRegion(sheet, cell.getRowIndex() - 1, columnIndex)) {
                            collectionName = ExcelUtil.getMergedRegionValue(sheet, cell.getRowIndex() - 1, columnIndex);
                            if (params.isIgnoreHeader(collectionName)) {
                                titlemap.put(cell.getColumnIndex(), value);
                            } else {
                                titlemap.put(cell.getColumnIndex(), collectionName + "_" + value);
                            }
                        } else {
                            String prefixTitle = (String)titlemap.get(cell.getColumnIndex());
                            if (prefixTitle != null && !"".equals(prefixTitle)) {
                                titlemap.put(cell.getColumnIndex(), prefixTitle + "_" + value);
                            } else {
                                titlemap.put(cell.getColumnIndex(), value);
                            }
                        }
                    }
                }
            }

            return titlemap;
        }
    }

    private ExcelCollectionParams getCollectionParams(List<ExcelCollectionParams> excelCollection, String collectionName) {
        Iterator var3 = excelCollection.iterator();

        ExcelCollectionParams excelCollectionParams;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            excelCollectionParams = (ExcelCollectionParams)var3.next();
        } while(!collectionName.equals(excelCollectionParams.getExcelName()));

        return excelCollectionParams;
    }

    public ExcelImportResult importExcelByIs(InputStream inputstream, Class<?> pojoClass, ImportParams params) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Excel import start ,class is {}", pojoClass);
        }

        List<T> result = new ArrayList();
        Workbook book = null;
        boolean isXSSFWorkbook = false;
        if (!((InputStream)inputstream).markSupported()) {
            inputstream = new PushbackInputStream((InputStream)inputstream, 8);
        }

        book = WorkbookFactory.create((InputStream)inputstream);
        if (book instanceof XSSFWorkbook) {
            isXSSFWorkbook = true;
        }

        LOGGER.info("  >>>  poi3升级到4.0兼容改造工作, isXSSFWorkbook = " + isXSSFWorkbook);
        if (params.getSheetNum() == 0) {
            int sheetNum = book.getNumberOfSheets();
            if (sheetNum > 0) {
                params.setSheetNum(sheetNum);
            }
        }

        this.createErrorCellStyle(book);
        String sheetName = params.getSheetName();

        for(int i = params.getStartSheetIndex(); i < params.getStartSheetIndex() + params.getSheetNum(); ++i) {
            if (sheetName != null && !"".equals(sheetName)) {
                Sheet tempSheet = book.getSheetAt(i);
                if (!sheetName.equals(tempSheet.getSheetName())) {
                    continue;
                }
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(" start to read excel by is ,startTime is {}", System.currentTimeMillis());
            }

            Map pictures;
            if (isXSSFWorkbook) {
                pictures = PoiPublicUtil.getSheetPictrues07((XSSFSheet)book.getSheetAt(i), (XSSFWorkbook)book);
            } else {
                pictures = PoiPublicUtil.getSheetPictrues03((HSSFSheet)book.getSheetAt(i), (HSSFWorkbook)book);
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(" end to read excel by is ,endTime is {}", (new Date()).getTime());
            }

            result.addAll(this.importExcel(result, book.getSheetAt(i), pojoClass, params, pictures));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(" end to read excel list by pos ,endTime is {}", (new Date()).getTime());
            }
        }

        if (params.isNeedSave()) {
            this.saveThisExcel(params, pojoClass, isXSSFWorkbook, book);
        }

        return new ExcelImportResult(result, this.verfiyFail, book);
    }

    public static byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[100000];

        int len;
        while((len = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, len);
        }

        buffer.flush();
        return buffer.toByteArray();
    }

    private void saveFieldValue(ImportParams params, Object object, Cell cell, Map<String, ExcelImportEntity> excelParams, String titleString, Row row) throws Exception {
        Object value = this.cellValueServer.getValue(params.getDataHanlder(), object, cell, excelParams, titleString);
        if (object instanceof Map) {
            if (params.getDataHanlder() != null) {
                params.getDataHanlder().setMapValue((Map)object, titleString, value);
            } else {
                ((Map)object).put(titleString, value);
            }
        } else {
            ExcelVerifyHanlderResult verifyResult = this.verifyHandlerServer.verifyData(object, value, titleString, ((ExcelImportEntity)excelParams.get(titleString)).getVerify(), params.getVerifyHanlder());
            if (!verifyResult.isSuccess()) {
                Cell errorCell = row.createCell(row.getLastCellNum());
                errorCell.setCellValue(verifyResult.getMsg());
                errorCell.setCellStyle(this.errorCellStyle);
                this.verfiyFail = true;
                throw new ExcelImportException(ExcelImportEnum.VERIFY_ERROR);
            }

            this.setValues((ExcelImportEntity)excelParams.get(titleString), object, value);
        }

    }

    private void saveImage(Object object, String picId, Map<String, ExcelImportEntity> excelParams, String titleString, Map<String, PictureData> pictures, ImportParams params) throws Exception {
        if (pictures != null && pictures.get(picId) != null) {
            PictureData image = (PictureData)pictures.get(picId);
            byte[] data = image.getData();
            String fileName = "pic" + Math.round(Math.random() * 1.0E11);
            fileName = fileName + "." + PoiPublicUtil.getFileExtendName(data);
            int saveType = ((ExcelImportEntity)excelParams.get(titleString)).getSaveType();
            if (saveType == 1) {
                String path = PoiPublicUtil.getWebRootPath(this.getSaveUrl((ExcelImportEntity)excelParams.get(titleString), object));
                File savefile = new File(path);
                if (!savefile.exists()) {
                    savefile.mkdirs();
                }

                savefile = new File(path + "/" + fileName);
                FileOutputStream fos = new FileOutputStream(savefile);
                fos.write(data);
                fos.close();
                this.setValues((ExcelImportEntity)excelParams.get(titleString), object, this.getSaveUrl((ExcelImportEntity)excelParams.get(titleString), object) + "/" + fileName);
            } else if (saveType == 2) {
                this.setValues((ExcelImportEntity)excelParams.get(titleString), object, data);
            } else {
                ImportFileServiceI importFileService = null;

                try {
                    importFileService = (ImportFileServiceI) ApplicationContextUtil.getContext().getBean(ImportFileServiceI.class);
                } catch (Exception var15) {
                    System.err.println(var15.getMessage());
                }

                if (importFileService != null) {
                    String saveUrl = ((ExcelImportEntity)excelParams.get(titleString)).getSaveUrl();
                    String dbPath;
                    if (StringUtils.isNotBlank(saveUrl)) {
                        LOGGER.debug("图片保存路径saveUrl = " + saveUrl);
                        Matcher matcher = lettersAndNumbersPattern.matcher(saveUrl);
                        if (!matcher.matches()) {
                            LOGGER.warn("图片保存路径格式错误，只能设置字母和数字的组合!");
                            dbPath = importFileService.doUpload(data);
                        } else {
                            dbPath = importFileService.doUpload(data, saveUrl);
                        }
                    } else {
                        dbPath = importFileService.doUpload(data);
                    }

                    this.setValues((ExcelImportEntity)excelParams.get(titleString), object, dbPath);
                }
            }

        }
    }

    private void createErrorCellStyle(Workbook workbook) {
        this.errorCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor((short)10);
        this.errorCellStyle.setFont(font);
    }
}
