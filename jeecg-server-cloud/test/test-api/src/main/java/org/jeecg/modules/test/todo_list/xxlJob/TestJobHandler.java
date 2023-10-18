package org.jeecg.modules.test.todo_list.xxlJob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestJobHandler {

    //@Autowired
    //private

    @XxlJob("motherFuck")
    public ReturnT<String> execute(String param){
        System.out.println("\n\nfucking fucker!");
        return new ReturnT<String>("默认返回值");
    }

    @XxlJob("selectTest")
    public ReturnT<String> selectTest(String param){
        System.out.println("\n\nselectTest selectTest!");
        return new ReturnT<String>("默认返回值");
    }

    @XxlJob("syncRoleByPost")
    public ReturnT<String> syncRoleByPost(String param){
        return null;
    }

    /*
    @XxlJob("testHandler")
    public void testHandler() throws Exception {
        System.out.println("\n\naaa>>>>>>>>>>>>");
        // default success
    }
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        System.out.println("\n\naaa>>>>>>>>>>>>");
        // default success
    }
    //通过职位更新角色
    @XxlJob(value="syncRoleByPost",init="init",destroy = "destory")
    public void syncRoleByPost()  throws Exception {
        System.out.println("\n\nAA!>>>>>>>>>>>>>>>>>.\n\n");
    }

    private void init(){}
    private void destory(){}
    */
}
