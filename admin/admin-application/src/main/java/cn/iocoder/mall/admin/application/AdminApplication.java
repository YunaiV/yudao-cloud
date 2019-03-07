package cn.iocoder.mall.admin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.admin"})
//@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
//        ConfigurableApplicationContext ctx =
//                System.out.println(); // TODO 后面去掉，这里是临时的
    }

}