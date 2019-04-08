package cn.iocoder.mall.cart.biz.config;

import cn.iocoder.common.framework.util.ServiceExceptionUtil;
import cn.iocoder.mall.cart.api.constant.CartErrorCodeEnum;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class ServiceExceptionConfiguration {

    @EventListener(ApplicationReadyEvent.class) // 可参考 https://www.cnblogs.com/ssslinppp/p/7607509.html
    public void initMessages() {
        for (CartErrorCodeEnum item : CartErrorCodeEnum.values()) {
            ServiceExceptionUtil.put(item.getCode(), item.getMessage());
        }
    }

}
