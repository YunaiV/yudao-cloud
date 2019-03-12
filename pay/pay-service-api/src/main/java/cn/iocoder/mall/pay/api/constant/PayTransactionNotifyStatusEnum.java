package cn.iocoder.mall.pay.api.constant;

/**
 * 支付交易通知状态枚举
 */
public enum PayTransactionNotifyStatusEnum {

    WAITING(1, "等待通知"),
    SUCCESS(2, "通知成功"),
    FAILURE(3, "通知失败"), // 多次尝试，彻底失败
    REQUEST_SUCCESS(4, "请求成功，但是结果失败"),
    REQUEST_FAILURE(5, "请求失败"),

    ;

    /**
     * 状态
     */
    private Integer value;
    /**
     * 名字
     */
    private String name;

    PayTransactionNotifyStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}