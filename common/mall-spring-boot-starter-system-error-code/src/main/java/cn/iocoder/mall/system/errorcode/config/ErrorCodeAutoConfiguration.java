package cn.iocoder.mall.system.errorcode.config;

import cn.iocoder.mall.system.errorcode.core.ErrorCodeAutoGenerator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ErrorCodeProperties.class)
public class ErrorCodeAutoConfiguration {

    @Bean
    public ErrorCodeAutoGenerator errorCodeAutoGenerator(ErrorCodeProperties errorCodeProperties) {
        return new ErrorCodeAutoGenerator(errorCodeProperties.getGroup())
                .setErrorCodeConstantsClass(errorCodeProperties.getConstantsClass());
    }

}
