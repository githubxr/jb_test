package org.jeecg.modules.test.entity.planOld;

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

@Data
@TableName("station")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "站位对象", description = "站位对象")
public class Station implements Serializable {
    @TableId(value = "station_id", type = IdType.ASSIGN_UUID)
    private String station_id; // 站位ID

    private String station_code; // 站位编号
    private BigDecimal x_coordinate; // X坐标
    private BigDecimal y_coordinate; // Y坐标
    private String detailed_address; // 详细地址
    private String sea_area_id; // 海域ID
}