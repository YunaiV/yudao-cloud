package cn.iocoder.mall.pay.api.constant;

/**
 * 支付退款状态枚举
 */
public enum PayRefundStatus {

    WAITING(1, "处理中"),
    SUCCESS(2, "成功"),
    FAILURE(3, "失败"), // 例如说，支付单超时
    ;

    /**
     * 状态
     */
    private Integer value;
    /**
     * 名字
     */
    private String name;

    PayRefundStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public PayRefundStatus setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public PayRefundStatus setName(String name) {
        this.name = name;
        return this;
    }

}
