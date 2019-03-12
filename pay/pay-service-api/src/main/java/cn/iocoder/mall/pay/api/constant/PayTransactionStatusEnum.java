package cn.iocoder.mall.pay.api.constant;

/**
 * 支付交易状态枚举
 */
public enum PayTransactionStatusEnum {

    WAITTING(1, "等待支付"),
    SUCCESS(2, "支付成功"),
    CANCEL(3, "取消支付"), // 例如说，支付单超时
    ;

    /**
     * 状态
     */
    private Integer value;
    /**
     * 名字
     */
    private String name;

    PayTransactionStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public PayTransactionStatusEnum setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public PayTransactionStatusEnum setName(String name) {
        this.name = name;
        return this;
    }

}