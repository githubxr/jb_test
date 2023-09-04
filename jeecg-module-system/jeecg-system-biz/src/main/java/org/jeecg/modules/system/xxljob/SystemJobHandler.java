package org.jeecg.modules.system.xxljob;

import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class SystemJobHandler {

    /**
     *
     * 通过职位更新角色
     * */
    @XxlJob("syncRoleByPost")
    public void syncRoleByPost(){

    }
}
