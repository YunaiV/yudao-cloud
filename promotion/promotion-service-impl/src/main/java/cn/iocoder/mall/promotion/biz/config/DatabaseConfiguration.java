package cn.iocoder.mall.promotion.biz.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan("cn.iocoder.mall.promotion.biz.dao") // 扫描对应的 Mapper 接口
@EnableTransactionManagement(proxyTargetClass = true) // 启动事务管理。为什么使用 proxyTargetClass 参数，参见 https://blog.csdn.net/huang_550/article/details/76492600
@EnableConfigurationProperties(DataSourceProperties.class)
public class DatabaseConfiguration {

    // 数据源，使用 HikariCP

    @Value("${spring.application.name}")
    private String applicationId;

    @Autowired
    private DataSourceProperties dataSourceProperties;

//    @Bean // TODO 芋艿，加了就一直报错，后面在找原因。
//    @Primary
    public DruidDataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(dataSourceProperties.getUrl());
        druidDataSource.setUsername(dataSourceProperties.getUsername());
        druidDataSource.setPassword(dataSourceProperties.getPassword());
        druidDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(0);
        druidDataSource.setMaxActive(180);
        druidDataSource.setMaxWait(60000);
        druidDataSource.setMinIdle(0);
        druidDataSource.setValidationQuery("Select 1 from DUAL");
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setTestWhileIdle(true);
        druidDataSource.setTimeBetweenEvictionRunsMillis(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(25200000);
        druidDataSource.setRemoveAbandoned(true);
        druidDataSource.setRemoveAbandonedTimeout(1800);
        druidDataSource.setLogAbandoned(true);
        return druidDataSource;
    }

    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    @Bean("dataSource")
//    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = druidDataSource();

        return new DataSourceProxy(dataSource);
    }

    /**
     * 注册一个StatViewServlet
     *
     * @return global transaction scanner
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner(applicationId, "my_test_tx_group");
        // TODO 芋艿，txServiceGroup 后续要编辑下
    }

}
