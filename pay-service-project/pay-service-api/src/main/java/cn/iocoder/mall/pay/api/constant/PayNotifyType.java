package cn.iocoder.mall.pay.api.constant;

/**
 * 支付通知类型
 */
public enum PayNotifyType {

    TRANSACTION(1, "支付"),
    REFUND(2, "退款"),
    ;

    /**
     * 类型
     */
    private Integer value;
    /**
     * 名字
     */
    private String name;

    PayNotifyType(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public PayNotifyType setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public PayNotifyType setName(String name) {
        this.name = name;
        return this;
    }

}
