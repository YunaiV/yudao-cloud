package cn.iocoder.mall.admin.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.admin"})
//@EnableAdminServer
public class AdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(AdminApplication.class, args);
//        Object bean = ctx.getBean("test");
//        System.out.println(AopUtils.getTargetClass(bean));

//        System.out.println(bean);

//        ConfigurableApplicationContext ctx =
//                System.out.println(); // TODO 后面去掉，这里是临时的
    }

}