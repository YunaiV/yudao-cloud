package cn.iocoder.mall.demo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.demo"})
@EnableAsync(proxyTargetClass = true)
//@PropertySource("classpath*:/application-dubbo.yaml")
public class DemoApplication {

    /**
     * 设置需要读取的配置文件的名字。
     * 基于 {@link org.springframework.boot.context.config.ConfigFileApplicationListener#CONFIG_NAME_PROPERTY} 实现。
     */
    private static final String CONFIG_NAME_VALUE = "application,rpc,business";

    public static void main(String[] args) {
        // 设置环境变量
        System.setProperty(ConfigFileApplicationListener.CONFIG_NAME_PROPERTY, CONFIG_NAME_VALUE);

        // 启动 Spring Boot 应用
        SpringApplication.run(DemoApplication.class, args);
    }

}
