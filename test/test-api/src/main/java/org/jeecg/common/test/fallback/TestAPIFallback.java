package org.jeecg.common.test.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.test.ITestApi;
import org.jeecg.common.api.dto.message.MessageDTO;

@Slf4j
public class TestAPIFallback implements ITestApi {
    @Setter
    private Throwable cause;

    @Override
    public void sendSysAnnouncement(MessageDTO message) {
        log.error("发送消息失败 {}", cause);
    }

}
