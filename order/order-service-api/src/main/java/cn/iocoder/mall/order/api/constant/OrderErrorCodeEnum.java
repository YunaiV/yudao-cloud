package cn.iocoder.mall.order.api.constant;

/**
 * 订单错误码
 *
 *  错误码区间 [1-008-000-000 ~ 1-008-000-000]
 *
 * @author Sin
 * @time 2019-03-23 11:23
 */
public enum OrderErrorCodeEnum {

    // order
    ORDER_NOT_EXISTENT(1008000000, "获取订单不存在!"),
    ORDER_GET_SKU_FAIL(1008000001, "获取商品失败!"),
    ORDER_GET_SKU_NOT_EXISTENT(1008000002, "获取的商品不存在!"),
    ORDER_PAY_AMOUNT_NOT_NEGATIVE(1008000003, "支付金额不能为负数!"),
    ORDER_STATUS_NOT_CANCEL(1008000004, "订单状态不能取消"),

    // order item
    ORDER_ITEM_ONLY_ONE(1008000004, "订单Item只有一个!"),
    ;

    private final int code;
    private final String message;

    OrderErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
