package cn.iocoder.mall.system.errorcode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mall.error-code")
public class ErrorCodeProperties {

    /**
     * 应用分组
     */
    private String group;
    /**
     * 错误码枚举类
     */
    private String constantsClass;

    public String getGroup() {
        return group;
    }

    public ErrorCodeProperties setGroup(String group) {
        this.group = group;
        return this;
    }

    public String getConstantsClass() {
        return constantsClass;
    }

    public ErrorCodeProperties setConstantsClass(String constantsClass) {
        this.constantsClass = constantsClass;
        return this;
    }
}
