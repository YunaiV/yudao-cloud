package cn.iocoder.mall.order.api.constant;

/**
 * 订单取消原因
 * order_cancel_reasons
 * @author Sin
 * @time 2019-03-30 15:08
 */
public enum OrderCancelReasonsEnum {

    CANCEL_1(1, "无法联系上买家"),
    CANCEL_2(2, "买家误拍或重拍了"),
    CANCEL_3(3, "买家无诚意完成交易"),
    CANCEL_4(4, "已通过银行线下汇款"),
    CANCEL_5(5, "已通过同城见面交易"),
    CANCEL_6(6, "已通过货到付款交易"),
    CANCEL_7(7, "已通过网上银行直接汇款"),
    CANCEL_8(8, "已经缺货无法交易"),
    CANCEL_20(20, "其他"),
    ;

    // 无法联系上买家
    // 买家误拍或重拍了
    // 买家无诚意完成交易
    // 已通过银行线下汇款
    // 已通过同城见面交易
    // 已通过货到付款交易
    // 已通过网上银行直接汇款
    // 已经缺货无法交易

    private final int code;
    private final String message;

    OrderCancelReasonsEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderCancelEnum{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
