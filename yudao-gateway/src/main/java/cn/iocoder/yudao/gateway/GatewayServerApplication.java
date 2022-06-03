package cn.iocoder.yudao.gateway;

import cn.iocoder.yudao.module.system.api.oauth2.OAuth2TokenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {
        OAuth2TokenApi.class
}) // TODO 芋艿：需要改下
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}
