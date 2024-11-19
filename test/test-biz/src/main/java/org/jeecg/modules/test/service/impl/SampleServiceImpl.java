package org.jeecg.modules.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.test.entity.Sample;
import org.jeecg.modules.test.mapper.SampleMapper;
import org.jeecg.modules.test.service.ISampleService;
import org.springframework.stereotype.Service;


/**
 * @Description: e
 * @Author: jeecg-boot
 * @Date:   2024-11-08
 * @Version: V1.0
 */
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements ISampleService {

    @Override
    public boolean existByCode(String sampleCode) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Sample::getSampleCode, sampleCode);
        return baseMapper.exists(queryWrapper);
    }
}

