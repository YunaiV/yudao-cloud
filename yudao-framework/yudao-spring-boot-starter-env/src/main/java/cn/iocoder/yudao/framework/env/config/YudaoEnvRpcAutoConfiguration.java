package cn.iocoder.yudao.framework.env.config;

import cn.iocoder.yudao.framework.env.core.fegin.EnvLoadBalancerClientFactory;
import cn.iocoder.yudao.framework.env.core.fegin.EnvRequestInterceptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClientsProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
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

    // TODO @芋艿：由于 loadBalancerClientFactoryBeanPostProcessor 拦截不到 LoadBalancerClientFactory，所以采用 loadBalancerClientFactory 实现
//    @Bean
//    public BeanPostProcessor loadBalancerClientFactoryBeanPostProcessor(LoadBalancerClientsProperties properties) {
//        return new BeanPostProcessor() {
//            @Override
//            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//                if (!(bean instanceof LoadBalancerClientFactory)) {
//                    return bean;
//                }
//                return bean;
//            }
//        };
//    }

    /**
     * 创建 {@link EnvLoadBalancerClientFactory} Bean
     *
     * 参考 {@link org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration#loadBalancerClientFactory(LoadBalancerClientsProperties)} 方法
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

    // ========== Dubbo 相关 ==========

}
