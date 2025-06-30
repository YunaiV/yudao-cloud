package cn.iocoder.yudao.module.infra.framework.rpc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "infraRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients()
public class RpcConfiguration {
}
