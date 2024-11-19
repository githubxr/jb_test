package org.jeecg.modules.test.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.generator.SnowflakeGenerator;
import cn.hutool.core.util.StrUtil;
import org.jeecg.boot.starter.lock.annotation.JLock;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.modules.test.entity.*;
import org.jeecg.modules.test.mapper.*;
import org.jeecg.modules.test.service.*;
import org.jeecg.modules.test.vo.EntryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.IdGenerator;

/**
 * @Description: test
 * @Author: jeecg-boot
 * @Date:   2024-11-07
 * @Version: V1.0
 */
@Service
public class EntryViewServiceImp extends ServiceImpl<EntryViewMapper, EntryView> implements IEntryViewService {

    @Autowired
    private ISysBaseAPI baseAPI;

    @Autowired
    private IShipService shipService;
    @Autowired
    private ISeaAreaService seaAreaService;
    @Autowired
    private IVoyageService voyageService;
    @Autowired
    private ISampleService sampleService;

    /**
     * @Transactional开启事务
     * @JLock 保证分布式锁环境下事务依旧安全有效
     * */
    @Transactional
    @JLock(lockKey = "'sample:lock:' + #ev.getSampleCode")
    @Override
    public void saveToMultSubTable(EntryView ev) {

        //step1：保存船
        String shipName = ev.getShipName();
        //String shipCode = baseAPI.translateDictFromTable("ship", "ship_code", "ship_name", shipName);
        Ship existShip = shipService.queryByName(shipName);
        String shipCode = null;
        //若该船名不存在就先保存船名
        if(existShip==null){
            shipCode = "ship_" + IdWorker.getId(); // 生成雪花ID
            //暂时将id和code设为一致，因为题意没有要求code的具体格式逻辑
            Ship newShip = new Ship(shipCode, shipCode, shipName);
            shipService.save(newShip);
        }else{
            shipCode = existShip.getShipCode();
        }

        //step2：保存海域
        String seaName = ev.getSeaAreaName();
        SeaArea existSea = seaAreaService.queryByName(seaName);
        String seaCode = null;
        //若海域不存在就先保存海域
        if(existSea == null){
            seaCode = "sea_" + IdWorker.getId(); //生成雪花ID
            //暂时将id和code设为一致，因为题意没有要求code的具体格式逻辑
            SeaArea newSeaArea = new SeaArea(seaCode, seaCode, seaName);
            seaAreaService.save(newSeaArea);
        } else {
            seaCode = existSea.getSeaAreaCode();
        }

        //step3：保存航次
        String voyageCode = ev.getVoyageCode();
        Voyage existVoyage = voyageService.queryByCode(voyageCode);
        //如果不存在
        if(existVoyage==null){
            Voyage voyage = new Voyage();
            voyage.setVoyageCode(voyageCode);
            voyage.setShipCode(shipCode);
            voyage.setSeaAreaCode(seaCode);
            voyageService.save(voyage);
        }

        //step4：保存测量样本
        String sampleCode = ev.getSampleCode();
        if(sampleService.existByCode(sampleCode)){
            throw new JeecgBootException("样本编号已存在：" + sampleCode);
        }
        Sample sample = new Sample();
        sample.setSampleCode(sampleCode);
        sample.setVoyageCode(voyageCode);

        sample.setSequenceNumber(ev.getSequenceNumber());
        sample.setStation(ev.getStation());
        sample.setCoordinateX(ev.getCoordinateX());
        sample.setCoordinateY(ev.getCoordinateY());
        sample.setEndDepth(ev.getEndDepth());
        sample.setHeartLength(ev.getHeartLength());
        sample.setDetailAddress(ev.getDetailAddress());

        sample.setStorageCondition(ev.getStorageCondition());
        sample.setStorageLocation(ev.getStorageLocation());
        sample.setNotes(ev.getNotes());
        sampleService.save(sample);

    }
}
