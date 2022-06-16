package cn.iocoder.mall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 短信 application (test)
 *
 * @author Sin
 * @time 2019/5/16 10:53 AM
 */
@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.admin"})
@EnableAsync(proxyTargetClass = true)
public class SystemApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplicationTest.class);
    }
}
