package cn.iocoder.mall.product.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cn.iocoder.mall.product"})
public class ProductApplication {

	public static void main(String[] args) {
	    SpringApplication.run(ProductApplication.class, args);
	}

}