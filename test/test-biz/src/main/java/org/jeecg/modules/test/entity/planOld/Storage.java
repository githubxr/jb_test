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

/**
 * 存储
 */
@Data
@TableName("storage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "存储对象", description = "存储对象")
public class Storage implements Serializable {
    @TableId(value = "storage_id", type = IdType.ASSIGN_UUID)
    private String storage_id; // 存储ID

    private String storage_location; // 存放位置
    private BigDecimal preservation_status; // 保存状况
}
