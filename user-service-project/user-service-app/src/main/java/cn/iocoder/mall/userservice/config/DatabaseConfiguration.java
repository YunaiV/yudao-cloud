package cn.iocoder.mall.userservice.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("cn.iocoder.mall.userservice.dal.mysql.mapper") // 扫描对应的 Mapper 接口
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理。
public class DatabaseConfiguration {

    // 数据库连接池 Druid

    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector(); // MyBatis Plus 逻辑删除
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor(); // MyBatis Plus 分页插件
    }

}
