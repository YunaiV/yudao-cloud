package cn.iocoder.mall.dubbo.core.filter;

import cn.iocoder.common.framework.util.StringUtils;
import cn.iocoder.mall.dubbo.core.cluster.interceptor.DubboConsumerRouterTagClusterInterceptor;
import cn.iocoder.mall.dubbo.core.router.DubboRouterTagContextHolder;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.cluster.router.tag.TagRouter;

/**
 * 基于 Dubbo 标签路由规则(http://dubbo.apache.org/zh-cn/docs/user/demos/routing-rule.html)，实现如下功能：
 * 1. 本地开发调试时，在带有 Dubbo Tag 的情况下，优先调用指定 Tag 的服务提供者。这样，我们可以将本地启动的服务提供者打上相应的 Tag，即可优先调用本地；
 *    并且，前端在调用开发环境上的 Dubbo 服务时，因为不带有 Dubbo Tag，所以不会调用到后端开发本地启动的 Dubbo 服务提供者；
 * 2. TODO 优化点：蓝绿发布、灰度发布
 *
 * 实现逻辑为：
 * 1. 对于 Consumer 方，在调用 Provider 时，{@link DubboConsumerRouterTagClusterInterceptor} 会将 {@link DubboRouterTagContextHolder} 中的 Tag 通过 Dubbo 隐式传参。
 *      同时，Dubbo 自带 {@link TagRouter}，会根据该参数，会选择符合该 Tag 的 Provider。
 * 2. 对于 Provider 方，在通过 Dubbo 隐式传参获得到 Tag 时，会设置到 {@link DubboRouterTagContextHolder} 中。
 *      这样，在 Provider 作为 Consumer 角色时，调用其它 Provider 时，可以继续实现标签路由的功能。
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER}, order = -1000)
public class DubboProviderRouterTagFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
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
