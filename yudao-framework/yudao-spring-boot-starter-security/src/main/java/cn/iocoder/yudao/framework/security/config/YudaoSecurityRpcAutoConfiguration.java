package cn.iocoder.yudao.framework.security.config;

import cn.iocoder.yudao.framework.common.biz.system.permission.PermissionCommonApi;
import cn.iocoder.yudao.framework.security.core.rpc.LoginUserRequestInterceptor;
import cn.iocoder.yudao.framework.common.biz.system.oauth2.OAuth2TokenCommonApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Security 使用到 Feign 的配置项
 *
 * @author 芋道源码
 */
@AutoConfiguration
@EnableFeignClients(clients = {OAuth2TokenCommonApi.class, // 主要是引入相关的 API 服务
        PermissionCommonApi.class})
public class YudaoSecurityRpcAutoConfiguration {

    @Bean
    public LoginUserRequestInterceptor loginUserRequestInterceptor() {
        return new LoginUserRequestInterceptor();
    }

}
