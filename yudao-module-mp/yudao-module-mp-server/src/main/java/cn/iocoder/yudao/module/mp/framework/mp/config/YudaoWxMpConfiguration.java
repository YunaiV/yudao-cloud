package cn.iocoder.yudao.module.mp.framework.mp.config;

import com.binarywang.spring.starter.wxjava.mp.properties.WxMpProperties;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.common.redis.WxRedisOps;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.WxMpHostConfig;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 微信公众号（weixin-java）的配置类
 *
 * weixin-java 4.8.x 的 {@code AbstractWxMpConfigStorageConfiguration} 在初始化 config storage 时，
 * 会无条件调用 {@code DefaultApacheHttpClientBuilder.get()}，后者在类加载时引用 Apache HttpClient 4.x 的
 * {@code org.apache.http.ssl.TrustStrategy} 等类，导致启动报 {@code NoClassDefFoundError}
 * （Spring Boot 4.x / 新版 Spring Cloud Alibaba 不再传递 HttpClient 4.x）。
 *
 * 本配置类自行创建 {@link WxMpConfigStorage} bean，绕过官方的初始化逻辑，
 * 配合 {@code http-client-type: HttpComponents} 配置，只依赖 Apache HttpClient 5.x。
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class YudaoWxMpConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = WxMpProperties.PREFIX + ".config-storage", name = "type", havingValue = "redistemplate")
    @ConditionalOnClass(StringRedisTemplate.class)
    @ConditionalOnMissingBean(WxMpConfigStorage.class)
    public WxMpConfigStorage wxMpConfigStorage(WxMpProperties properties, ApplicationContext applicationContext) {
        StringRedisTemplate redisTemplate = applicationContext.getBean(StringRedisTemplate.class);
        WxRedisOps redisOps = new RedisTemplateWxRedisOps(redisTemplate);
        WxMpRedisConfigImpl config = new WxMpRedisConfigImpl(redisOps, properties.getConfigStorage().getKeyPrefix());
        applyWxMpConfig(config, properties);
        return config;
    }

    private void applyWxMpConfig(WxMpDefaultConfigImpl config, WxMpProperties properties) {
        config.setAppId(properties.getAppId());
        config.setSecret(properties.getSecret());
        config.setToken(properties.getToken());
        config.setAesKey(properties.getAesKey());
        config.setUseStableAccessToken(properties.isUseStableAccessToken());

        WxMpProperties.ConfigStorage storage = properties.getConfigStorage();
        config.setHttpProxyHost(storage.getHttpProxyHost());
        config.setHttpProxyUsername(storage.getHttpProxyUsername());
        config.setHttpProxyPassword(storage.getHttpProxyPassword());
        if (storage.getHttpProxyPort() != null) {
            config.setHttpProxyPort(storage.getHttpProxyPort());
        }

        if (properties.getHosts() != null && StringUtils.isNotEmpty(properties.getHosts().getApiHost())) {
            WxMpHostConfig hostConfig = new WxMpHostConfig();
            hostConfig.setApiHost(properties.getHosts().getApiHost());
            hostConfig.setOpenHost(properties.getHosts().getOpenHost());
            hostConfig.setMpHost(properties.getHosts().getMpHost());
            config.setHostConfig(hostConfig);
        }
    }

}
