package cn.iocoder.yudao.module.prod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * @author 芋道源码
 */
@SpringBootApplication
public class ProdServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdServerApplication.class, args);
    }

}
