package cn.iocoder.mall.spring.boot.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("swagger")
@Deprecated
public class SwaggerProperties {

    private String title;
    private String description;
    private String version;
    private String basePackage;

}
