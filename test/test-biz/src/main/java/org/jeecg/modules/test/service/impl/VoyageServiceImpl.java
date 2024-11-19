package org.jeecg.modules.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.test.entity.Ship;
import org.jeecg.modules.test.entity.Voyage;
import org.jeecg.modules.test.mapper.ShipMapper;
import org.jeecg.modules.test.mapper.VoyageMapper;
import org.jeecg.modules.test.service.IShipService;
import org.jeecg.modules.test.service.IVoyageService;
import org.springframework.stereotype.Service;

import javax.management.Query;


/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
@Service
public class VoyageServiceImpl extends ServiceImpl<VoyageMapper, Voyage> implements IVoyageService {

    @Override
    public boolean existByVoyageCode(String voyageCode) {
        QueryWrapper<Voyage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Voyage::getVoyageCode, voyageCode);
        return baseMapper.exists(queryWrapper);
    }

    @Override
    public Voyage queryByCode(String voyageCode) {
        QueryWrapper<Voyage> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Voyage::getVoyageCode, voyageCode);
        return baseMapper.selectOne(queryWrapper);
    }
}

