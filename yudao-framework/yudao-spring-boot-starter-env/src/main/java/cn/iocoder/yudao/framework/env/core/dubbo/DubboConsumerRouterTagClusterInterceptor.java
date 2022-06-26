package cn.iocoder.yudao.framework.env.core.dubbo;

import cn.iocoder.yudao.framework.env.core.context.EnvContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.cluster.interceptor.ClusterInterceptor;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import org.springframework.util.StringUtils;

/**
 * Consumer 方，在调用 Provider 时，将 {@link EnvContextHolder} 中的 Tag 通过 Dubbo 隐式传参。
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
        String tag = EnvContextHolder.getTag();
        if (StringUtils.hasText(tag)) {
            invocation.setAttachment(CommonConstants.TAG_KEY, tag);
        }
    }

    @Override
    public void after(AbstractClusterInvoker<?> clusterInvoker, Invocation invocation) {
        // 清空 Dubbo Tag 的隐式传参
        invocation.setAttachment(CommonConstants.TAG_KEY, null);
    }

}
