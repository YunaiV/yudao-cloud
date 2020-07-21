package cn.iocoder.mall.dubbo.core.filter;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.dubbo.core.router.DubboRouterTagContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * 基于 Dubbo 标签路由规则(http://dubbo.apache.org/zh-cn/docs/user/demos/routing-rule.html)，实现如下功能：
 * 1. 本地开发调试时，在带有 Dubbo Tag 的情况下，优先调用指定 Tag 的服务提供者。这样，我们可以将本地启动的服务提供者打上相应的 Tag，即可优先调用本地；
 * 2. TODO 优化点：蓝绿发布、灰度发布
 *
 * 实现逻辑为：
 * 1. 对于 Consumer 方，在调用 Provider 时，会将 {@link DubboRouterTagContextHolder} 中的 Tag 通过 Dubbo 隐式传参。
 *      同时，Dubbo 自带 {@link org.apache.dubbo.rpc.cluster.router.tag.TagRouter}，会根据该参数，会选择符合该 Tag 的 Provider。
 * 2. 对于 Provider 方，在通过 Dubbo 隐式传参获得到 Tag 时，会设置到 {@link DubboRouterTagContextHolder} 中。
 *      这样，在 Provider 作为 Consumer 角色时，调用其它 Provider 时，可以继续实现标签路由的功能。
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = -1000)
public class DubboRouterTagFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // 消费端
        if (RpcContext.getContext().isConsumerSide()) {
            // 设置 Dubbo Tag 到 Dubbo 隐式传参
            String dubboTag = DubboRouterTagContextHolder.getTag();
            boolean hasDubboTag = StringUtils.hasText(dubboTag);
            if (hasDubboTag) {
                invocation.setAttachment(CommonConstants.TAG_KEY, dubboTag);
            }
            // 继续调用
            try {
                return invoker.invoke(invocation);
            } finally {
                // 解决极端情况下，本地 injvm 调用时，消费端会调用 DubboRouterTagContextHolder.clear() 上下文，导致消费端也被清理了，因为在同一个 JVM 进程内。
                if (hasDubboTag) {
                    DubboRouterTagContextHolder.setTag(dubboTag);
                }
            }
            // 提供端
        } else {
            // 从 Dubbo 隐式传参获得 Dubbo Tag
            String dubboTag = invocation.getAttachment(CommonConstants.TAG_KEY);
            boolean hasDubboTag = StringUtils.hasText(dubboTag);
            if (hasDubboTag) {
                invocation.setAttachment(CommonConstants.TAG_KEY, dubboTag);
            }
            // 继续调用
            try {
                return invoker.invoke(invocation);
            } finally {
                // 清理
                if (hasDubboTag) {
                    DubboRouterTagContextHolder.clear();
                }
            }
        }
    }

}
