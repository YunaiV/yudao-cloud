package cn.iocoder.mall.systemservice.config;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.systemservice.enums.SystemErrorCodeEnum;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.util.Arrays;

@Configuration
public class ServiceExceptionConfiguration {

    @EventListener(ApplicationReadyEvent.class)
    public void initMessages() {
        Arrays.stream(SystemErrorCodeEnum.values()).forEach(
                item -> ServiceExceptionUtil.put(item.getCode(), item.getMessage()));
    }

}
