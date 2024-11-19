package org.jeecg.modules.test.service;
import org.jeecg.modules.test.vo.EntryView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: test
 * @Author: jeecg-boot
 * @Date:   2024-11-07
 * @Version: V1.0
 */
public interface IEntryViewService extends IService<EntryView> {

    /**
     * 事务保存到多个子表
     * */
    void saveToMultSubTable(EntryView ev);

}