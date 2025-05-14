package cn.iocoder.yudao.module.bpm.framework.rpc.config;

import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.PostApi;
import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import cn.iocoder.yudao.module.system.api.permission.PermissionApi;
import cn.iocoder.yudao.module.system.api.permission.RoleApi;
import cn.iocoder.yudao.module.system.api.sms.SmsSendApi;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "bpmRpcConfiguration", proxyBeanMethods = false)
@EnableFeignClients(clients = {RoleApi.class, DeptApi.class, PostApi.class, AdminUserApi.class, SmsSendApi.class, DictDataApi.class,
        PermissionApi.class})
public class RpcConfiguration {
}
