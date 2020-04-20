package cn.iocoder.mall.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 简单的 Swagger2 自动配置类
 *
 * 较为完善的，可以了解 https://mvnrepository.com/artifact/com.spring4all/spring-boot-starter-swagger
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass({Docket.class, ApiInfoBuilder.class})
@ConditionalOnProperty(prefix = "swagger", value = "enable", matchIfMissing = true) // 允许使用 swagger.enable=false 禁用 Swagger
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    @Bean
    public Docket createRestApi() {
        SwaggerProperties properties = swaggerProperties();
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(properties))
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .version(properties.getVersion())
                .build();
    }

}
