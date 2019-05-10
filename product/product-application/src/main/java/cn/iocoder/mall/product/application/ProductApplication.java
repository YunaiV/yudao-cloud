package cn.iocoder.mall.product.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.product"})
@EnableAsync(proxyTargetClass = true)
public class ProductApplication {

	public static void main(String[] args) {
	    SpringApplication.run(ProductApplication.class, args);
	}

}
