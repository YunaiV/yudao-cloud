package cn.iocoder.mall.payservice.enums.transaction;

import lombok.Getter;

/**
 * 支付交易状态枚举
 */
@Getter
public enum PayTransactionStatusEnum {

    WAITING(1, "等待支付"),
    SUCCESS(2, "支付成功"),
    CANCEL(3, "取消支付"), // 例如说，支付单超时
    ;

    /**
     * 状态
     */
    private final Integer status;
    /**
     * 名字
     */
    private final String name;

    PayTransactionStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

}
