package cn.iocoder.yudao.module.report.framework.rpc.config;

import cn.iocoder.yudao.module.infra.api.config.ConfigApi;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.infra.api.websocket.WebSocketSenderApi;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "reportRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {PermissionApi.class})
public class RpcConfiguration {
}
