package cn.iocoder.mall.promotionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync(proxyTargetClass = true) // 开启 Spring Async 异步的功能
public class AsyncConfiguration {
}
