package cn.iocoder.yudao.module.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** HRM 模块独立启动入口。 */
@SpringBootApplication
public class HrmServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrmServerApplication.class, args);
    }
}
