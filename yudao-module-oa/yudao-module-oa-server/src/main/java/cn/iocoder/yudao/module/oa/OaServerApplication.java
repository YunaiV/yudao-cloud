package cn.iocoder.yudao.module.oa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** OA 模块独立启动入口。 */
@SpringBootApplication
public class OaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OaServerApplication.class, args);
    }
}
