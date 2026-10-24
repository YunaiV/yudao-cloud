package cn.iocoder.yudao.module.system.seatademo;

import cn.hutool.core.util.StrUtil;
import feign.RequestInterceptor;
import org.apache.seata.core.context.RootContext;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 分布式事务示例的 RPC 配置类
 *
 * 1. 单独声明示例的 Feign 客户端，不修改 system-server 已有的 RpcConfiguration
 * 2. 在全局事务中发起 Feign 调用时，通过 TX_XID 请求头传递 XID，被调用方由 Seata 自带的 HTTP 拦截器绑定
 *
 * @author 芋道源码
 */
@Configuration(value = "demoSeataRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = DemoSeataAccountApi.class)
public class DemoSeataRpcConfiguration {

    /**
     * 传递 XID 的 Feign 拦截器
     *
     * 对当前服务的所有 Feign 调用生效，但只在全局事务中（存在 XID）时添加请求头，其它调用不受影响
     */
    @Bean
    public RequestInterceptor demoSeataXidRequestInterceptor() {
        return template -> {
            String xid = RootContext.getXID();
            if (StrUtil.isNotEmpty(xid)) {
                template.header(RootContext.KEY_XID, xid);
            }
        };
    }

}
