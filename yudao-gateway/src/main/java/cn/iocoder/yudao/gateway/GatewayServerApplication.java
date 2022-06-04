package cn.iocoder.yudao.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(GatewayServerApplication.class, args);

        // 启动 Spring Boot 应用
        ConfigurableApplicationContext context = SpringApplication.run(GatewayServerApplication.class, args);

        // 查看 Environment
        Environment environment = context.getEnvironment();
        System.out.println(environment);
    }

}
