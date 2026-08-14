package cn.iocoder.yudao.module.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** FMS 模块独立启动入口。 */
@SpringBootApplication
public class FmsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FmsServerApplication.class, args);
    }
}
