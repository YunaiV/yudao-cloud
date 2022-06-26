package cn.iocoder.yudao.framework.tenant.core.mq;

import cn.hutool.core.map.MapUtil;
import cn.iocoder.yudao.framework.tenant.core.util.TenantUtils;
import org.springframework.cloud.function.context.catalog.FunctionAroundWrapper;
import org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry;
import org.springframework.messaging.Message;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.HEADER_TENANT_ID;

/**
 * 多租户 FunctionAroundWrapper 实现类
 * 消费消息时，设置租户编号到 Context 上
 *
 * @author 芋道源码
 */
public class TenantFunctionAroundWrapper extends FunctionAroundWrapper {

    @Override
    protected Object doApply(Object input, SimpleFunctionRegistry.FunctionInvocationWrapper targetFunction) {
        // 如果不是 MQ 消息，则直接跳过
        if (!(input instanceof Message)) {
            return targetFunction.apply(input);
        }
        // 如果没有多租户，则直接跳过
        Message<?> message = (Message<?>) input;
        Long tenantId = MapUtil.getLong(message.getHeaders(), HEADER_TENANT_ID);
        if (tenantId == null) {
            return targetFunction.apply(input);
        }

        // 如果有多租户，则使用多租户上下文
        return TenantUtils.execute(tenantId, () -> targetFunction.apply(input));
    }

}
