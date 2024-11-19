package org.jeecg.modules.test.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @description 入库单视图类
 * @remark 注意这是（联表）视图而非表
 */
@TableName("entry_view")
@Data
@ApiModel(value = "入库单视图对象", description = "对应Excel文件中的入库单数据")
public class EntryView implements Serializable {

    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;

//    /**创建人*/
//    @ApiModelProperty(value = "创建人")
//    private java.lang.String createBy;
//    /**创建日期*/
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "创建日期")
//    private java.util.Date createTime;
//    /**更新人*/
//    @ApiModelProperty(value = "更新人")
//    private java.lang.String updateBy;
//    /**更新日期*/
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "更新日期")
//    private java.util.Date updateTime;
//    /**所属部门*/
//    @ApiModelProperty(value = "所属部门")
//    private java.lang.String sysOrgCode;


    @Excel(name = "样品编号", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "样品编号")
    private java.lang.String sampleCode;

    @Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private java.lang.Integer sequenceNumber;

    //无需自动转换-导入需要从中文翻译为code
    //@Excel(name = "调查船", width = 15), dictTable = "ship", dicCode = "ship_code", dicText = "ship_name")
    //@Dict(dictTable = "ship", dicCode = "ship_code", dicText = "ship_name")
    //@NotNull
    //@ExcelVerify(notNull = true)

    //@Dict(dictTable = "ship", dicCode = "ship_code", dicText = "ship_name")
    @ApiModelProperty(value = "调查船编号")
    private java.lang.String shipCode;

    //导入使用
    @Excel(name = "调查船", width = 15)
    @ApiModelProperty(value = "调查船")
    private java.lang.String shipName;

    @Excel(name = "航次", width = 15)
    @ApiModelProperty(value = "航次")
    private java.lang.String voyageCode;

    ////无需自动转换-导入需要从中文翻译为code
    //@Excel(name = "详细位置_海域", width = 15), dictTable = "sea_area", dicCode = "sea_area_code", dicText = "sea_area_name")
    //@ExcelVerify(notNull = true)
    //@Dict(dictTable = "sea_area", dicCode = "sea_area_code", dicText = "sea_area_name")
    @ApiModelProperty(value = "详细位置_海域编号")
    private java.lang.String seaAreaCode;

    //导入使用
    @Excel(name = "详细位置_海域", width = 15)
    @ApiModelProperty(value = "详细位置_海域")
    private java.lang.String seaAreaName;

    @Excel(name = "详细位置_站位", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "详细位置_站位")
    private java.lang.String station;

    @Excel(name = "详细位置_X坐标", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "详细位置_X坐标")
    private java.lang.Double coordinateX;

    @Excel(name = "详细位置_Y坐标", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "详细位置_Y坐标")
    private java.lang.Double coordinateY;

    @Excel(name = "详细位置_详细地址", width = 15)
    @ApiModelProperty(value = "详细位置_详细地址")
    private java.lang.String detailAddress;

    @Excel(name = "结束深度（m）", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "结束深度（m）")
    private java.lang.Double endDepth;

    @Excel(name = "心长（m）", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "心长（m）")
    private java.lang.Double heartLength;


    @Excel(name = "存放位置", width = 15)
    @ExcelVerify(notNull = true)
    @ApiModelProperty(value = "存放位置")
    private java.lang.String storageLocation;

    @Excel(name = "保存状况", width = 15)
    @ApiModelProperty(value = "保存状况")
    private java.lang.String storageCondition;


    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String notes;

}
