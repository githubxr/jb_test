package org.jeecg.modules.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.test.entity.Sample;
import org.jeecg.modules.test.entity.Ship;

/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
public interface ISampleService extends IService<Sample> {
    /**
     * 根据code判定是否存在
     * */
    boolean existByCode(String sampleCode);
}
