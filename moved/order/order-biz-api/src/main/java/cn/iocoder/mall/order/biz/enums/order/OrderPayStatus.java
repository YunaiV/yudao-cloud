package cn.iocoder.mall.order.biz.enums.order;

/**
 * 订单状态 status
 *
 * @author Sin
 * @time 2019-03-16 14:32
 */
public enum OrderPayStatus {

    WAITING_PAYMENT(0, "等待支付"),
    SUCCESSFUL_PAYMENT(1, "支付成功"),
    REFUND_PAYMENT(2, "退款成功"),

    ;


    private final int value;

    private final String name;

    OrderPayStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
