package cn.iocoder.mall.tradeservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties("biz")
@Validated
@Data
public class TradeBizProperties {

    /**
     * 支付应用编号
     *
     * 由 pay-service 支付系统提供
     */
    @NotNull(message = "支付应用编号不能为空")
    private String payAppId;

    /**
     * 支付超时时间，单位：分
     */
    @NotNull(message = "支付超时时间不能为空")
    private Integer payExpireTime;

}
