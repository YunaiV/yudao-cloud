package cn.iocoder.mall.order.api.constants;

/**
 * 订单 - status
 *
 * @author Sin
 * @time 2019-03-16 14:06
 */
public enum OrderStatusEnum {

    // 基本状态
    WAIT_SHIPMENT(0, "等待发货"),
    ALREADY_SHIPMENT(1, "已发货"),
    RECEIVED_GOODS(2, "已收货"),
    CONFIRM_RECEIPT_GOODS(3, "确认收货"), // 确认收货，也就是交易成功！

    // 换货
    APPLY_EXCHANGE_GOODS(20, "申请换货"),
    EXCHANGE_GOODS(21, "换货中"),
    EXCHANGE_GOODS_SUCCESSFUL(22, "换货成功"),

    // 退货
    APPLY_RETURN_GOODS(40, "申请退货"),
    RETURN_GOODS(41, "退货中"),
    RETURN_GOODS_SUCCESSFUL(42, "退货成功"),

    ;

    private final int value;

    private final String name;

    OrderStatusEnum(int value, String name) {
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
