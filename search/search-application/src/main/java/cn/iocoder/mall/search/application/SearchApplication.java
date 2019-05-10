package cn.iocoder.mall.search.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.search"})
@EnableAsync(proxyTargetClass = true)
public class SearchApplication {

    public static void main(String[] args) {
        // 解决 ES  java.lang.IllegalStateException: availableProcessors is already
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class, args);
    }

}
