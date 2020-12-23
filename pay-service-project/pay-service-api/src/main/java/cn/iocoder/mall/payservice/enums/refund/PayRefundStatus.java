package cn.iocoder.mall.payservice.enums.refund;

import lombok.Getter;

/**
 * 支付退款状态枚举
 */
@Getter
public enum PayRefundStatus {

    WAITING(1, "处理中"),
    SUCCESS(2, "成功"),
    FAILURE(3, "失败"), // 例如说，支付单超时
    ;

    /**
     * 状态
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    PayRefundStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

}
