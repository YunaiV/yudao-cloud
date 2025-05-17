package cn.iocoder.yudao.module.report.framework.rpc.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "reportRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients()
public class RpcConfiguration {
}
