package cn.iocoder.yudao.framework.tenant.core.mq;

import cn.hutool.core.util.ReflectUtil;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.Map;

import static cn.iocoder.yudao.framework.web.core.util.WebFrameworkUtils.HEADER_TENANT_ID;

/**
 * 多租户的 {@link ChannelInterceptor} 实现类
 * 发送消息时，设置租户编号到 Header 上
 *
 * @author 芋道源码
 */
public class TenantChannelInterceptor implements ChannelInterceptor {

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null) {
            Map<String, Object> headers = (Map<String, Object>) ReflectUtil.getFieldValue(message.getHeaders(), "headers");
            headers.put(HEADER_TENANT_ID, tenantId);
        }
        return message;
    }

}
