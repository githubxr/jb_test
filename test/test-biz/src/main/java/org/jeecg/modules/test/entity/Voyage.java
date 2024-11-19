package org.jeecg.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
/**
 * 航次
 */
@Data
@TableName("voyage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "航次对象", description = "航次对象")
public class Voyage implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id; // 航次ID

    private String voyageCode;
    private String voyageName;
    private String shipCode; // 外键关联 SurveyShip
    private String seaAreaCode; // 外键关联 SeaArea
}