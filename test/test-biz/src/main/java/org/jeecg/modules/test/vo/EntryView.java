package org.jeecg.modules.test.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;


/**
 * 入库单视图类
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
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
    /**序号*/
    @Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
    private java.lang.Integer sequenceNumber;
    /**样品编号*/
    @Excel(name = "样品编号", width = 15)
    @ApiModelProperty(value = "样品编号")
    private java.lang.String sampleCode;
    /**调查船*/
    @Excel(name = "调查船", width = 15)
    @ApiModelProperty(value = "调查船")
    private java.lang.String surveyShip;
    /**航次*/
    @Excel(name = "航次", width = 15)
    @ApiModelProperty(value = "航次")
    private java.lang.String voyage;
    /**海域*/
    @Excel(name = "详细位置_海域", width = 15)
    @ApiModelProperty(value = "详细位置_海域")
    private java.lang.String seaArea;
    /**站位*/
    @Excel(name = "详细位置_站位", width = 15)
    @ApiModelProperty(value = "详细位置_站位")
    private java.lang.String station;
    /**X坐标*/
    @Excel(name = "详细位置_X坐标", width = 15)
    @ApiModelProperty(value = "详细位置_X坐标")
    private java.lang.Double coordinateX;
    /**Y坐标*/
    @Excel(name = "详细位置_Y坐标", width = 15)
    @ApiModelProperty(value = "详细位置_Y坐标")
    private java.lang.Double coordinateY;
    /**详细地址*/
    @Excel(name = "详细位置_详细地址", width = 15)
    @ApiModelProperty(value = "详细位置_详细地址")
    private java.lang.String detailedAddress;
    /**结束深度（m）*/
    @Excel(name = "结束深度（m）", width = 15)
    @ApiModelProperty(value = "结束深度（m）")
    private java.lang.Double endDepth;
    /**心长（m）*/
    @Excel(name = "心长（m）", width = 15)
    @ApiModelProperty(value = "心长（m）")
    private java.lang.Double heartLength;
    /**存放位置*/
    @Excel(name = "存放位置", width = 15)
    @ApiModelProperty(value = "存放位置")
    private java.lang.String storageLocation;
    /**保存状况*/
    @Excel(name = "保存状况", width = 15)
    @ApiModelProperty(value = "保存状况")
    private java.lang.String preservationStatus;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remarks;

}
