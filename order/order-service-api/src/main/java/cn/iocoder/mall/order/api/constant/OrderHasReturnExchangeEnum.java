package cn.iocoder.mall.order.api.constant;

/**
 * 订单 - 是否退换货状态
 *
 * @author Sin
 * @time 2019-03-22 21:34
 */
public enum OrderHasReturnExchangeEnum {

    NO(1, "没有"),
    RETURN_GOODS(2, "退货"),
    EXCHANGE_GOODS(3, "换货"),
    RETURN_EXCHANGE_GOODS(4, "退换货");

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    OrderHasReturnExchangeEnum(Integer value, String name) {
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
