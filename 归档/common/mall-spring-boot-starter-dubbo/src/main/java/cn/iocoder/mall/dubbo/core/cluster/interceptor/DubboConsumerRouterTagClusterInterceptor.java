package cn.iocoder.mall.dubbo.core.cluster.interceptor;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.dubbo.core.filter.DubboProviderRouterTagFilter;
import cn.iocoder.mall.dubbo.core.router.DubboRouterTagContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.cluster.interceptor.ClusterInterceptor;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;

/**
 * Consumer 方，在调用 Provider 时，将 {@link DubboRouterTagContextHolder} 中的 Tag 通过 Dubbo 隐式传参。
 *
 * 完整逻辑说明，见 {@link DubboProviderRouterTagFilter}
 *
 * 注意，这里需要设置到 order = 1 的原因，是需要保证排在 ConsumerContextClusterInterceptor 之后
 */
@Activate(group = CommonConstants.CONSUMER, order = 1)
public class DubboConsumerRouterTagClusterInterceptor implements ClusterInterceptor {

    @Override
    public void before(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        // 设置 Dubbo Tag 到 Dubbo 隐式传参
        String dubboTag = DubboRouterTagContextHolder.getTag();
        if (StringUtils.hasText(dubboTag)) {
            invocation.setAttachment(CommonConstants.TAG_KEY, dubboTag);
        }
    }

    @Override
    public void after(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        // 清空 Dubbo Tag 的隐式传参
        invocation.setAttachment(CommonConstants.TAG_KEY, null);
    }

}
