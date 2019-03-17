package cn.iocoder.mall.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.order"})
public class OrderApplication {

	public static void main(String[] args) {
	    SpringApplication.run(OrderApplication.class, args);
	}

}