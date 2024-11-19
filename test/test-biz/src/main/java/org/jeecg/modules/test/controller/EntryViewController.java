package org.jeecg.modules.test.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;

import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.test.service.IEntryViewService;
import org.jeecg.modules.test.vo.EntryView;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: test
 * @Author: jeecg-boot
 * @Date:   2024-11-07
 * @Version: V1.0
 */
@Api(tags="entryview")
@RestController
@RequestMapping("/test/entryview")
@Slf4j
public class EntryViewController extends JeecgController<EntryView, IEntryViewService> {

    @Autowired
    private IEntryViewService entryViewService;

    @AutoLog(value = "test-a")
    @ApiOperation(value="test-a", notes="test-a")
    //@RequiresPermissions("test:testt:edit")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody Object str ) {
        //testtService.updateById(testt);
        return Result.OK("访问成功!");
    }


    /**
     *   通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "test-通过id删除")
    @ApiOperation(value="test-通过id删除", notes="test-通过id删除")
    //@RequiresPermissions("pack:testt:delete")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        service.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     *  批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "test-批量删除")
    @ApiOperation(value="test-批量删除", notes="test-批量删除")
    //@RequiresPermissions("pack:testt:deleteBatch")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 分页列表查询
     *
     * @param v
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    //@AutoLog(value = "test-分页列表查询")
    @ApiOperation(value="test-分页列表查询", notes="test-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<EntryView>> queryPageList(EntryView v,
                                              @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                              @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                              HttpServletRequest req) {
        QueryWrapper<EntryView> queryWrapper = QueryGenerator.initQueryWrapper(v, req.getParameterMap());
        queryWrapper.orderByAsc("sequence_number");
        Page<EntryView> page = new Page<EntryView>(pageNo, pageSize);
        IPage<EntryView> pageList = entryViewService.page(page, queryWrapper);
        return Result.OK(pageList);
    }



    /**
     * 导出excel
     *
     * @param request
     * @param entryView
     */
    //@RequiresPermissions("pack:testt:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, EntryView entryView) {
        return super.exportXls(request, entryView, EntryView.class, "entryView");
    }


    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    //@RequiresPermissions("test:entryview:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        //return super.importExcel(request, response, EntryView.class);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 获取上传文件对象
            MultipartFile file = entity.getValue();
            ImportParams params = new ImportParams();
//            params.setTitleRows(2);
//            params.setHeadRows(1);
            params.setTitleRows(1);
            params.setHeadRows(2);
            params.setNeedSave(true);
            try {
                List<EntryView> list = ExcelImportUtil.importExcel(file.getInputStream(), EntryView.class, params);

                //update-begin-author:taoyan date:20190528 for:批量插入数据
                long start = System.currentTimeMillis();

                int index=0;
                for(EntryView ev :list) {
                    index++;
                    service.saveToMultSubTable(ev);
                }

                //service.saveBatch(list);
                System.out.println("能成功执行！");
                //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
                //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
                log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
                //update-end-author:taoyan date:20190528 for:批量插入数据
                return Result.ok("文件导入成功！数据行数：" + list.size());
            } catch (Exception e) {
                //update-begin-author:taoyan date:20211124 for: 导入数据重复增加提示
                String msg = e.getMessage();
                log.error(msg, e);
                if(msg!=null && msg.indexOf("Duplicate entry")>=0){
                    return Result.error("文件导入失败:有重复数据！");
                }else{
                    return Result.error("文件导入失败:" + e.getMessage());
                }
                //update-end-author:taoyan date:20211124 for: 导入数据重复增加提示
            } finally {
                try {
                    file.getInputStream().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.error("文件导入失败！");

    }
}
