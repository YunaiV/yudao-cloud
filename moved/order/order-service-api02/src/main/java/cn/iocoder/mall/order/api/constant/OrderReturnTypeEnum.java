package cn.iocoder.mall.order.api.constant;

/**
 * 订单退货 类型
 *
 * @author Sin
 * @time 2019-03-30 15:42
 */
public enum OrderReturnTypeEnum {

    ORDER(1, "订单"),
    ORDER_ITEM(2, "订单item"),
    ;

    private final int value;

    private final String name;

    OrderReturnTypeEnum(int value, String name) {
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
