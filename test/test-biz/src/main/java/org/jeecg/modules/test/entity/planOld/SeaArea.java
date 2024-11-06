package org.jeecg.modules.test.entity.planOld;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("sea_area")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "海域对象", description = "海域对象")
public class SeaArea implements Serializable {
    @TableId(value = "sea_area_id", type = IdType.ASSIGN_UUID)
    private String sea_area_id; // 海域ID

    private String sea_area_name; // 海域名称
}
