package cn.iocoder.mall.swagger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("swagger")
public class SwaggerProperties {

    private String title;
    private String description;
    private String version;
    private String basePackage;

    public String getTitle() {
        return title;
    }

    public SwaggerProperties setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SwaggerProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public SwaggerProperties setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public SwaggerProperties setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return this;
    }
}
