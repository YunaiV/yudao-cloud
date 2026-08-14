package cn.iocoder.yudao.module.hrm.framework.rpc.config;

import cn.iocoder.yudao.module.bpm.api.task.BpmProcessInstanceApi;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.logger.OperateLogApi;
import cn.iocoder.yudao.module.system.api.notify.NotifyMessageSendApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "hrmRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {AdminUserApi.class, DeptApi.class, OperateLogApi.class,
        NotifyMessageSendApi.class, BpmProcessInstanceApi.class})
public class RpcConfiguration {
}
