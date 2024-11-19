package org.jeecg.modules.test.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 存储
 */
@Data
@TableName("sample")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "存储对象", description = "存储对象")
public class Sample implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id; // 存储ID

    private Integer sequenceNumber;
    private String sampleCode;
    private String voyageCode; // 外键关联 SurveyVoyage

    private String station;
    private double coordinateX;
    private double coordinateY;
    private double endDepth;
    private double heartLength;
    private String detailAddress;

    private String storageLocation;
    private String storageCondition;
    private String notes;
}
