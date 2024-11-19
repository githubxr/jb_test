package org.jeecg.modules.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.test.entity.Ship;

/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
public interface IShipService extends IService<Ship> {

    /**
     * 通过名称获取
     * */
    Ship queryByName(String shipName);
}
