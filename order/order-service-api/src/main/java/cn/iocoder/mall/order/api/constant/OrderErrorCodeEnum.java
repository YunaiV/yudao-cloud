package cn.iocoder.mall.order.api.constant;

/**
 * 订单错误码
 *
 *  错误码区间 [1-000-001-000 ~ 1-000-002-000]
 *
 * @author Sin
 * @time 2019-03-23 11:23
 */
public enum OrderErrorCodeEnum {

    ORDER_ITEM_ONLY_ONE(1000001000, "订单Item只有一个!"),
    ORDER_GET_SKU_FAIL(1000001001, "获取商品失败!"),
    ORDER_GET_SKU_NOT_EXISTENT(1000001002, "获取的商品不存在!"),
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
