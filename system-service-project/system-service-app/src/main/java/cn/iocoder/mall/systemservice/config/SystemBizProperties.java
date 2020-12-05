package cn.iocoder.mall.systemservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 系统服务的业务配置项
 */
@Component
@ConfigurationProperties("biz")
@Validated
@Data
public class SystemBizProperties {

    /**
     * 访问令牌过期时间，单位：毫秒
     */
    @NotNull(message = "访问令牌过期时间不能为空")
    private int accessTokenExpireTimeMillis;
    /**
     * 刷新令牌过期时间，单位：毫秒
     */
    @NotNull(message = "刷新令牌过期时间不能为空")
    private int refreshTokenExpireTimeMillis;

}
