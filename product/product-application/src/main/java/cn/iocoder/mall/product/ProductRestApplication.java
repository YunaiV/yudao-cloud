package cn.iocoder.mall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("cn.iocoder.mall.product.dao") // 扫描对应的 Mapper 接口
public class ProductRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProductRestApplication.class, args);
		DataSource ds = ctx.getBean(DataSource.class);
		System.out.println(ds);
	}

}