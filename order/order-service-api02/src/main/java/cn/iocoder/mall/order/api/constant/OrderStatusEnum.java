package cn.iocoder.mall.order.api.constant;

/**
 * 订单 - status
 *
 * @author Sin
 * @time 2019-03-16 14:06
 */
public enum OrderStatusEnum {

    WAITING_PAYMENT(1, "等待付款"),
    WAIT_SHIPMENT(2, "等待发货"),
    ALREADY_SHIPMENT(3, "已发货"),
    COMPLETED(4, "已完成"),
    CLOSED(5, "已关闭");

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

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
