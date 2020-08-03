package cn.iocoder.mall.mybatis.config;

import cn.iocoder.mall.mybatis.core.injector.CustomSqlInject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;

/**
 * @author Hccake 2020/8/3
 * @version 1.0
 */
public class MybatisPlusAutoConfiguration {

    /**
     * 自定义方法扩展注入器
     * @return ISqlInjector CustomSqlInject
     */
    @Bean
    @ConditionalOnMissingBean(ISqlInjector.class)
    public ISqlInjector sqlInjector(){
        return new CustomSqlInject();
    }
}
