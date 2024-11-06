package org.jeecg.common.test;

import org.jeecg.common.api.dto.message.*;
import org.jeecg.common.constant.ServiceNameConstants;
import org.jeecg.common.test.factory.TestAPIFallbackFactory;
import org.jeecg.common.test.fallback.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@FeignClient(contextId = "testRemoteApi", value = ServiceNameConstants.SERVICE_TEST, fallbackFactory = TestAPIFallbackFactory.class)
@ConditionalOnMissingClass("org.jeecg.modules.test.service.impl.SysBaseApiImpl")
public interface ITestApi {

        /**
         * 1发送系统消息
         * @param message 使用构造器赋值参数 如果不设置category(消息类型)则默认为2 发送系统消息
         */
        @PostMapping("/sys/api/sendSysAnnouncement")
        void sendSysAnnouncement(@RequestBody MessageDTO message);

}
