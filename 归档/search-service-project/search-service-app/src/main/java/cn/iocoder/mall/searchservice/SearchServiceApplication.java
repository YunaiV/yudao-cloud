package cn.iocoder.mall.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"cn.iocoder.mall.productservice.rpc"})
public class SearchServiceApplication {

    public static void main(String[] args) {
        // 解决 ES  java.lang.IllegalStateException: availableProcessors is already
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchServiceApplication.class, args);
    }

}
