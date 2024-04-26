package cn.iocoder.yudao.framework.env.config;

import cn.iocoder.yudao.framework.env.core.fegin.EnvLoadBalancerClientFactory;
import cn.iocoder.yudao.framework.env.core.fegin.EnvRequestInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClientsProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

/**
 * 多环境的 RPC 组件的自动配置
 *
 * @author 芋道源码
 */
@AutoConfiguration
@EnableConfigurationProperties(EnvProperties.class)
public class YudaoEnvRpcAutoConfiguration {

    // ========== Feign 相关 ==========

    /**
     * 创建 {@link EnvLoadBalancerClientFactory} Bean
     *
     * 参考 {@link LoadBalancerAutoConfiguration#loadBalancerClientFactory(LoadBalancerClientsProperties)} 方法
     */
    @Bean
    public LoadBalancerClientFactory loadBalancerClientFactory(LoadBalancerClientsProperties properties,
                                                               ObjectProvider<List<LoadBalancerClientSpecification>> configurations) {
        EnvLoadBalancerClientFactory clientFactory = new EnvLoadBalancerClientFactory(properties);
        clientFactory.setConfigurations(configurations.getIfAvailable(Collections::emptyList));
        return clientFactory;
    }

    @Bean
    public EnvRequestInterceptor envRequestInterceptor() {
        return new EnvRequestInterceptor();
    }

}
