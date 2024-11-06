package org.jeecg.modules.test.entity.planOld;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 测量记录
 */
@Data
@TableName("measurement_record")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "测量记录对象", description = "测量记录对象")
public class MeasurementRecord implements Serializable {
    @TableId(value = "record_id", type = IdType.ASSIGN_UUID)
    private String record_id; // 测量记录ID
    private String sample_code; // 样品编号

    private String voyage_id; // 航次ID
    private String station_id; // 站位ID
    private BigDecimal end_depth; // 结束深度（m）
    private BigDecimal heart_length; // 心长（m）
    private String remarks; // 备注
}
