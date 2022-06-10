package cn.iocoder.yudao.framework.security.config;

import cn.iocoder.yudao.module.system.api.oauth2.OAuth2TokenApi;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * Security 使用到 Feign 的配置项
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = {OAuth2TokenApi.class, // 主要是引入相关的 API 服务
        PermissionApi.class})
public class YudaoSecurityFeignAutoConfiguration {
}
