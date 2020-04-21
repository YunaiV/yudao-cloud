package cn.iocoder.mall.security.config;

import cn.iocoder.mall.security.core.interceptor.AccountAuthInterceptor;
import cn.iocoder.mall.web.config.CommonWebAutoConfiguration;
import cn.iocoder.mall.web.core.constant.CommonMallConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AutoConfigureAfter(CommonWebAutoConfiguration.class) // 在 CommonWebAutoConfiguration 之后自动配置，保证过滤器的顺序
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CommonSecurityAutoConfiguration implements WebMvcConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // ========== 拦截器相关 ==========
    @Bean
    @ConditionalOnMissingBean(AccountAuthInterceptor.class)
    public AccountAuthInterceptor accountAuthInterceptor() {
        return new AccountAuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // AccountAuthInterceptor 拦截器
        registry.addInterceptor(this.accountAuthInterceptor())
                .addPathPatterns(CommonMallConstants.ROOT_PATH_ADMIN + "/**", CommonMallConstants.ROOT_PATH_USER + "/**");
        logger.info("[addInterceptors][加载 AccountAuthInterceptor 拦截器完成]");
    }

}
