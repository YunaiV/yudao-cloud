package cn.iocoder.mall.system.biz.config;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfiguration {

    @Value("${qiniu.access-key}")
    private String accessKey;
    @Value("${qiniu.secret-key}")
    private String secretKey;

    @Bean
    public Auth auth() {
        return Auth.create(accessKey, secretKey);
    }

}
