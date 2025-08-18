package cn.iocoder.yudao.framework.env.core.fegin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.env.core.context.EnvContextHolder;
import cn.iocoder.yudao.framework.env.core.util.EnvUtils;
import com.alibaba.cloud.nacos.balancer.NacosBalancer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 多环境的 {@link org.springframework.cloud.client.loadbalancer.LoadBalancerClient} 实现类
 * 在从服务实例列表选择时，优先选择 tag 匹配的服务实例
 *
 * @author 芋道源码
 */
@RequiredArgsConstructor
@Slf4j
public class EnvLoadBalancerClient implements ReactorServiceInstanceLoadBalancer {

    /**
     * 用于获取 serviceId 对应的服务实例的列表
     */
    private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    /**
     * 需要获取的服务实例名
     *
     * 暂时用于打印 logger 日志
     */
    private final String serviceId;
    /**
     * 被代理的 ReactiveLoadBalancer 对象
     */
    private final ReactiveLoadBalancer<ServiceInstance> reactiveLoadBalancer;

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return supplier.get(request).next().map(list -> {
            // 情况一，没有 tag 时，过滤掉有 tag 的节点。目的：避免 test 环境，打到本地有 tag 的实例
            String tag = EnvContextHolder.getTag();
            if (StrUtil.isEmpty(tag)) {
                return getInstanceResponseWithoutTag(list);
            }

            // 情况二，有 tag 时，使用 tag 匹配服务实例
            return getInstanceResponse(list, tag);
        });
    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances, String tag) {
        // 如果服务实例为空，则直接返回
        if (CollUtil.isEmpty(instances)) {
            log.warn("[getInstanceResponse][serviceId({}) 服务实例列表为空]", serviceId);
            return new EmptyResponse();
        }

        // 筛选满足条件的实例列表
        List<ServiceInstance> chooseInstances = CollectionUtils.filterList(instances, instance -> tag.equals(EnvUtils.getTag(instance)));
        if (CollUtil.isEmpty(chooseInstances)) {
            log.warn("[getInstanceResponse][serviceId({}) 没有满足 tag({}) 的服务实例列表，直接使用所有服务实例列表]", serviceId, tag);
            chooseInstances = instances;
        }

        // TODO 芋艿：https://juejin.cn/post/7056770721858469896 相同网段

        // 随机 + 权重获取实例列表 TODO 芋艿：目前直接使用 Nacos 提供的方法，如果替换注册中心，需要重新失败该方法
        return new DefaultResponse(NacosBalancer.getHostByRandomWeight3(chooseInstances));
    }

    /**
     * 当没有 tag 时，过滤掉有 tag 的实例列表
     */
    private Response<ServiceInstance> getInstanceResponseWithoutTag(List<ServiceInstance> instances) {
        // 如果服务实例为空，则直接返回
        if (CollUtil.isEmpty(instances)) {
            log.warn("[getInstanceResponseWithoutTag][serviceId({}) 服务实例列表为空]", serviceId);
            return new EmptyResponse();
        }

        // 筛选没有 tag 的实例列表
        List<ServiceInstance> chooseInstances = CollectionUtils.filterList(instances, instance -> StrUtil.isEmpty(EnvUtils.getTag(instance)));
        // 【重要】补充说明：如果希望在 chooseInstances 为空时，不允许打到有 tag 的实例，可以取消注释下面的代码
        if (CollUtil.isEmpty(chooseInstances)) {
            chooseInstances = instances;
        }

        // 随机 + 权重获取实例列表
        return new DefaultResponse(NacosBalancer.getHostByRandomWeight3(chooseInstances));
    }

}
