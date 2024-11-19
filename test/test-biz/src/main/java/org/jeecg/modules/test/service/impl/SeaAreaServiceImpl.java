package org.jeecg.modules.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.test.entity.SeaArea;
import org.jeecg.modules.test.entity.Ship;
import org.jeecg.modules.test.mapper.SeaAreaMapper;
import org.jeecg.modules.test.mapper.ShipMapper;
import org.jeecg.modules.test.service.ISeaAreaService;
import org.jeecg.modules.test.service.IShipService;
import org.springframework.stereotype.Service;


/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
@Service
public class SeaAreaServiceImpl extends ServiceImpl<SeaAreaMapper, SeaArea> implements ISeaAreaService {

    @Override
    public SeaArea queryByName(String seaAreaName) {
        QueryWrapper<SeaArea> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SeaArea::getSeaAreaName, seaAreaName);
        return baseMapper.selectOne(queryWrapper);
    }
}

