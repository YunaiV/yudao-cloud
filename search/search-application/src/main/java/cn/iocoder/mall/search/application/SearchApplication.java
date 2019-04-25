package cn.iocoder.mall.search.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.search"})
public class SearchApplication {

    public static void main(String[] args) {
        // 解决 ES  java.lang.IllegalStateException: availableProcessors is already
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchApplication.class, args);
    }

}
