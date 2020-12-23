package cn.iocoder.mall.xxljob.config;

import com.xxl.job.core.executor.XxlJobExecutor;
import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * XXL-Job 自动配置类
 */
@Configuration
@ConditionalOnClass(XxlJobSpringExecutor.class)
@ConditionalOnProperty(prefix = "xxl.job", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({XxlJobProperties.class})
public class XxlJobAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(XxlJobAutoConfiguration.class);

    private final XxlJobProperties properties;

    public XxlJobAutoConfiguration(XxlJobProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public XxlJobExecutor xxlJobExecutor() {
        LOGGER.info("初始化 XXL-Job 执行器的配置");

        // 参数校验
        XxlJobProperties.AdminProperties admin = this.properties.getAdmin();
        XxlJobProperties.ExecutorProperties executor = this.properties.getExecutor();
        Objects.requireNonNull(admin, "xxl job admin properties must not be null.");
        Objects.requireNonNull(executor, "xxl job executor properties must not be null.");

        // 初始化执行器
        XxlJobExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setIp(executor.getIp());
        xxlJobExecutor.setPort(executor.getPort());
        xxlJobExecutor.setAppname(executor.getAppName());
        xxlJobExecutor.setLogPath(executor.getLogPath());
        xxlJobExecutor.setLogRetentionDays(executor.getLogRetentionDays());
        xxlJobExecutor.setAdminAddresses(admin.getAddresses());
        xxlJobExecutor.setAccessToken(this.properties.getAccessToken());
        return xxlJobExecutor;
    }

}
