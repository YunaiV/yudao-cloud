package cn.iocoder.mall.order.biz.enums.order;

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
    ORDER_CREATE_CART_IS_EMPTY(1008000013, "购物车无选中的商品，无法创建订单"),
    ORDER_STATUS_NOT_WAITING_PAYMENT(1008000014, "订单不处于等待支付状态"),
    ORDER_PAY_AMOUNT_ERROR(1008000015, "订单金额不正确"),

    // order item
    ORDER_ITEM_ONLY_ONE(1008000200, "订单Item只有一个!"),
    ORDER_ITEM_SOME_NOT_EXISTS(1008000201, "有不存在的商品!"),

    // 订单退货
    ORDER_RETURN_NO_RETURN_APPLY(1008000400, "未退货申请"),
    ORDER_RETURN_NOT_EXISTENT(1008000401, "退货订单不存在"),
    ORDER_RETURN_REFUND_FAILED(1008000402, "退款失败"),

    // ========== 购物车 ==========
    CARD_ITEM_NOT_FOUND(1008003000, "购物车项不存在"),
    CARD_ITEM_SKU_NOT_FOUND(1008003001, "商品不存在"),
    CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH(1008003002, "商品库存不足"),

    // 工具类服务  1008004000
    DICT_SERVER_INVOKING_FAIL(1008004000, "字典服务调用失败!"),
    ORDER_LOGISTICS_INVOKING_FAIL(1008004001, "订单物流调用失败!"),


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
