package org.jeecg.common.test.factory;

import org.jeecg.common.test.ITestApi;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.jeecg.common.test.fallback.*;

@Component
public class TestAPIFallbackFactory implements FallbackFactory<ITestApi> {

    @Override
    public ITestApi create(Throwable throwable) {
        TestAPIFallback fallback = new TestAPIFallback();
        fallback.setCause(throwable);
        return fallback;
    }
}
