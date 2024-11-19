package org.jeecg.modules.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.test.entity.SeaArea;
import org.jeecg.modules.test.entity.Voyage;

/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
public interface IVoyageService extends IService<Voyage> {
    /**
     * 是否存在
     * */
    boolean existByVoyageCode(String voyageCode);

    /**
     * 通过编号获取
     * */
    Voyage queryByCode(String voyageCode);
}
