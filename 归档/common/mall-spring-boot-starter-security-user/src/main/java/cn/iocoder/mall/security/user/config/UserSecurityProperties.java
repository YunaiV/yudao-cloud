package cn.iocoder.mall.security.user.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mall.security.user")
public class UserSecurityProperties {

    private static final String[] DEFAULT_IGNORE_PATHS = new String[]{
            // Swagger 相关
            "/doc.html", "/swagger-resources",  "/swagger-resources/**", "/webjars/**",
            // Actuator 相关
    };

    /**
     * 自定义忽略 Path
     */
    private String[] ignorePaths = new String[0];
    /**
     * 默认忽略 Path
     */
    private String[] defaultIgnorePaths = DEFAULT_IGNORE_PATHS;

    public String[] getIgnorePaths() {
        return ignorePaths;
    }

    public UserSecurityProperties setIgnorePaths(String[] ignorePaths) {
        this.ignorePaths = ignorePaths;
        return this;
    }

    public String[] getDefaultIgnorePaths() {
        return defaultIgnorePaths;
    }

    public UserSecurityProperties setDefaultIgnorePaths(String[] defaultIgnorePaths) {
        this.defaultIgnorePaths = defaultIgnorePaths;
        return this;
    }

}
