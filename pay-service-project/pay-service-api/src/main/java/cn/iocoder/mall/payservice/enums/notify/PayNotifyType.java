package cn.iocoder.mall.payservice.enums.notify;

import lombok.Getter;

/**
 * 支付通知类型
 */
@Getter
public enum PayNotifyType {

    TRANSACTION(1, "支付"),
    REFUND(2, "退款"),
    ;

    /**
     * 类型
     */
    private final Integer type;
    /**
     * 名字
     */
    private final String name;

    PayNotifyType(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

}
