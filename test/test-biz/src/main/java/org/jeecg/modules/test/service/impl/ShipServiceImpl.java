package org.jeecg.modules.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.test.entity.Ship;
import org.jeecg.modules.test.mapper.ShipMapper;
import org.jeecg.modules.test.service.IShipService;
import org.springframework.stereotype.Service;


/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
@Service
public class ShipServiceImpl extends ServiceImpl<ShipMapper, Ship> implements IShipService {

    @Override
    public Ship queryByName(String shipName) {
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Ship::getShipName, shipName);
        return baseMapper.selectOne(queryWrapper);
    }

}

