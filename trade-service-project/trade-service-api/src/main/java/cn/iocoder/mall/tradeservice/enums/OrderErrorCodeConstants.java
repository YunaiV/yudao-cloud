package cn.iocoder.mall.tradeservice.enums;

import cn.iocoder.common.framework.exception.ErrorCode;

/**
 * 订单错误码
 * <p>
 * 错误码区间 [1-008-000-000 ~ 1-008-000-000]
 *
 * @author Sin
 * @time 2019-03-23 11:23
 */
public interface OrderErrorCodeConstants {

    // order
    ErrorCode ORDER_NOT_EXISTENT = new ErrorCode(1008000000, "获取订单不存在!");
    ErrorCode ORDER_GET_SKU_FAIL = new ErrorCode(1008000001, "获取商品失败!)");
    ErrorCode ORDER_GET_SKU_NOT_EXISTENT = new ErrorCode(1008000002, "获取的商品不存在!");
    ErrorCode ORDER_PAY_AMOUNT_NOT_NEGATIVE = new ErrorCode(1008000003, "支付金额不能为负数!");
    ErrorCode ORDER_STATUS_NOT_CANCEL = new ErrorCode(1008000004, "订单状态不能取消!)");
    ErrorCode ORDER_DELIVERY_INCORRECT_DATA = new ErrorCode(1008000005, "订单发货数据不正确!");
    ErrorCode ORDER_INSUFFICIENT_INVENTORY = new ErrorCode(1008000006, "库存不足!");
    ErrorCode ORDER_GOODS_AMOUNT_INCORRECT = new ErrorCode(1008000007, "商品金额非法!");
    ErrorCode ORDER_GET_GOODS_INFO_INCORRECT = new ErrorCode(1008000008, "获取额商品信息不正确!");
    ErrorCode ORDER_GET_USER_ADDRESS_FAIL = new ErrorCode(1008000009, "获取用户地址失败!");
    ErrorCode ORDER_GET_PAY_FAIL = new ErrorCode(1008000010, "调用pay失败!");
    ErrorCode ORDER_NOT_USER_ORDER = new ErrorCode(1008000011, "不是该用户的订单!");
    ErrorCode ORDER_UNABLE_CONFIRM_ORDER = new ErrorCode(1008000012, "状态不对不能确认订单!");
    ErrorCode ORDER_CREATE_CART_IS_EMPTY = new ErrorCode(1008000013, "购物车无选中的商品，无法创建订单");
    ErrorCode ORDER_STATUS_NOT_WAITING_PAYMENT = new ErrorCode(1008000014, "订单不处于等待支付状态");
    ErrorCode ORDER_PAY_AMOUNT_ERROR = new ErrorCode(1008000015, "订单金额不正确");

    // order item
    ErrorCode ORDER_ITEM_ONLY_ONE = new ErrorCode(1008000200, "订单Item只有一个!");
    ErrorCode ORDER_ITEM_SOME_NOT_EXISTS = new ErrorCode(1008000201, "有不存在的商品!");

    // 订单退货
    ErrorCode ORDER_RETURN_NO_RETURN_APPLY = new ErrorCode(1008000400, "未退货申请");
    ErrorCode ORDER_RETURN_NOT_EXISTENT = new ErrorCode(1008000401, "退货订单不存在");
    ErrorCode ORDER_RETURN_REFUND_FAILED = new ErrorCode(1008000402, "退款失败");

    // ========== 购物车 ==========
    ErrorCode CARD_ITEM_NOT_FOUND = new ErrorCode(1008003000, "购物车项不存在");
    ErrorCode CARD_ITEM_SKU_NOT_FOUND = new ErrorCode(1008003001, "商品不存在");
    ErrorCode CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH = new ErrorCode(1008003002, "商品库存不足");

    // 工具类服务  1008004000
    ErrorCode DICT_SERVER_INVOKING_FAIL = new ErrorCode(1008004000, "字典服务调用失败!");
    ErrorCode ORDER_LOGISTICS_INVOKING_FAIL = new ErrorCode(1008004001, "订单物流调用失败!");

}
