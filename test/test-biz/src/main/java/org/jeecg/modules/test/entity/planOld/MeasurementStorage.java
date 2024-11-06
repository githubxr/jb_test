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
 * 测量存储
 */
@Data
@TableName("measurement_storage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "测量存储对象", description = "测量存储对象")
public class MeasurementStorage implements Serializable {
    private String record_id; // 测量记录ID
    private String storage_id; // 存储ID
}