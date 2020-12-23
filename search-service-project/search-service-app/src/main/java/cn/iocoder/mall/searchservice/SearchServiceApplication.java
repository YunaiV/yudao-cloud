package cn.iocoder.mall.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchServiceApplication {

    public static void main(String[] args) {
        // 解决 ES  java.lang.IllegalStateException: availableProcessors is already
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(SearchServiceApplication.class, args);
    }

}
