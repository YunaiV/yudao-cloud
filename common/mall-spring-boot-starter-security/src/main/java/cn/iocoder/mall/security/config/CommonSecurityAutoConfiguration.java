package cn.iocoder.mall.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(name = {"cn.iocoder.mall.system.rpc.api.systemlog.SystemLogRPC", "org.apache.dubbo.config.annotation.Reference"})
public class CommonSecurityAutoConfiguration implements WebMvcConfigurer {

    // ========== 拦截器相关 ==========


}
