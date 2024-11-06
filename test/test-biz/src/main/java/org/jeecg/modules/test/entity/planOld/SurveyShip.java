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

/**
 * 船
 * */
@Data
@TableName("survey_ship")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="testt对象", description="test")
public class SurveyShip implements Serializable {

    @TableId(value = "ship_id", type = IdType.ASSIGN_UUID)
    private String ship_id;
    private String ship_name;
}
