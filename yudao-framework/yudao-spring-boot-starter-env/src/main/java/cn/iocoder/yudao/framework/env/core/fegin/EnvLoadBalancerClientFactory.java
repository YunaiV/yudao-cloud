package cn.iocoder.yudao.framework.env.core.fegin;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClientsProperties;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;

/**
 * 多环境的 {@link LoadBalancerClientFactory} 实现类
 * 目的：在创建 {@link ReactiveLoadBalancer} 时，会额外增加 {@link EnvLoadBalancerClient} 代理，用于 tag 过滤服务实例
 *
 * @author 芋道源码
 */
public class EnvLoadBalancerClientFactory extends LoadBalancerClientFactory {

    public EnvLoadBalancerClientFactory(LoadBalancerClientsProperties properties) {
        super(properties);
    }

    @Override
    public ReactiveLoadBalancer<ServiceInstance> getInstance(String serviceId) {
        ReactiveLoadBalancer<ServiceInstance> reactiveLoadBalancer = super.getInstance(serviceId);
        // 参考 {@link com.alibaba.cloud.nacos.loadbalancer.NacosLoadBalancerClientConfiguration#nacosLoadBalancer(Environment, LoadBalancerClientFactory, NacosDiscoveryProperties)} 方法
        return new EnvLoadBalancerClient(super.getLazyProvider(serviceId, ServiceInstanceListSupplier.class),
                serviceId, reactiveLoadBalancer);
    }

}
