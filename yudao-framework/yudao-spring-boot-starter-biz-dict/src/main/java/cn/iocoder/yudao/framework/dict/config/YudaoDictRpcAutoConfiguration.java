package cn.iocoder.yudao.framework.dict.config;

import cn.iocoder.yudao.module.system.api.dict.DictDataApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * 字典用到 Feign 的配置项
 *
 * @author 芋道源码
 */
@Configuration(proxyBeanMethods = false)
@EnableFeignClients(clients = DictDataApi.class) // 主要是引入相关的 API 服务
public class YudaoDictRpcAutoConfiguration {
}
