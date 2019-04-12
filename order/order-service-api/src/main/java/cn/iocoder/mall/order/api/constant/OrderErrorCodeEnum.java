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
    ORDER_STATUS_NOT_CANCEL(1008000004, "订单状态不能取消!"),
    ORDER_DELIVERY_INCORRECT_DATA(1008000005, "订单发货数据不正确!"),
    ORDER_INSUFFICIENT_INVENTORY(1008000006, "库存不足!"),
    ORDER_GOODS_AMOUNT_INCORRECT(1008000007, "商品金额非法!"),
    ORDER_GET_GOODS_INFO_INCORRECT(1008000008, "获取额商品信息不正确!"),
    ORDER_GET_USER_ADDRESS_FAIL(1008000009, "获取用户地址失败!"),
    ORDER_GET_PAY_FAIL(1008000010, "调用pay失败!"),
    ORDER_NOT_USER_ORDER(1008000011, "不是该用户的订单!"),
    ORDER_UNABLE_CONFIRM_ORDER(1008000012, "状态不对不能确认订单!"),

    // order item
    ORDER_ITEM_ONLY_ONE(1008000200, "订单Item只有一个!"),
    ORDER_ITEM_SOME_NOT_EXISTS(1008000201, "有不存在的商品!"),


    // ========== 购物车 ==========
    CARD_ITEM_NOT_FOUND(1008003000, "购物车项不存在"),
    CARD_ITEM_SKU_NOT_FOUND(1008003001, "商品不存在"),
    CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH(1008003002, "商品库存不足"),

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
