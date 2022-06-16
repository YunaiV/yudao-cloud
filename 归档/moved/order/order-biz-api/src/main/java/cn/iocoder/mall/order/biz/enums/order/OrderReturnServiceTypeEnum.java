package cn.iocoder.mall.order.biz.enums.order;

/**
 * 订单退货 - returnType
 *
 * @author Sin
 * @time 2019-04-27 11:53
 */
public enum OrderReturnServiceTypeEnum {

    /**
     * 状态
     *
     * - 1、退货退款
     * - 2、退款
     */
    RETURN_REFUND(1, "退货退款"),
    REFUND(2, "退款")
    ;
    private final int value;

    private final String name;

    OrderReturnServiceTypeEnum(int value, String name) {
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
