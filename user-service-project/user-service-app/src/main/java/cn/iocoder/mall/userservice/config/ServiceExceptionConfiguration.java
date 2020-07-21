package cn.iocoder.mall.userservice.config;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.mall.userservice.enums.UserErrorCodeConstants;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@Configuration
public class ServiceExceptionConfiguration {

    @EventListener(ApplicationReadyEvent.class)
    public void initMessages() {
        Arrays.stream(UserErrorCodeConstants.values()).forEach(
                item -> ServiceExceptionUtil.put(item.getCode(), item.getMessage()));
    }

}
