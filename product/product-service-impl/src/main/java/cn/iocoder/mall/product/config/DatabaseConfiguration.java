package cn.iocoder.mall.product.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan("cn.iocoder.mall.product.dao") // 扫描对应的 Mapper 接口
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理。为什么使用 proxyTargetClass 参数，参见 https://blog.csdn.net/huang_550/article/details/76492600
public class DatabaseConfiguration {

    @Value("${spring.application.name}")
    private String applicationId;
    @Value("${seata.tx-service-group}")
    private String txServiceGroup;

    @Bean("druidDataSource")
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource druidDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    @Bean("dataSource")
    @DependsOn("druidDataSource") // 解决多数据源，循环依赖的问题。主要发生点在 DataSourceInitializerInvoker
    public DataSource dataSource() {
        DruidDataSource druidDataSource = druidDataSource();
        return new DataSourceProxy(druidDataSource);
    }

    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner(applicationId, txServiceGroup);
    }

}
