package org.jeecg.modules.executor;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class ExecutorHandle_1 {

    //测试任务1
    @XxlJob(value="eh1")
    public void task1(){
        XxlJobHelper.log("\n\neh1执行一次!\n");
        System.out.println("\n\n eh1任务1 执行！\n");
    }
}
