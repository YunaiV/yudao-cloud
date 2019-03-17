package cn.iocoder.mall.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.iocoder.mall.order")
public class OrderApplicationTest {

	public static void main(String[] args) {
	    SpringApplication.run(OrderApplicationTest.class, args);
	}

}