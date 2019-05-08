package cn.iocoder.mall.order.api.constant;

/**
 * 订单退货 status
 *
 * @author Sin
 * @time 2019-03-30 15:56
 */
public enum OrderReturnStatusEnum {

    /**
     * 状态
     *
     * - 1、退货申请
     * - 2、申请成功
     * - 3、申请失败
     * - 4、退货中
     * - 5、已收货
     * - 6、拒绝退款
     */
    RETURN_APPLICATION(1, "退货申请"),
    APPLICATION_SUCCESSFUL(2, "申请成功"),
    APPLICATION_FAIL(3, "申请失败"),
    RETURN_IN(4, "退货中"),
    ORDER_RECEIPT(5, "确认收货"),
    RETURN_SUCCESS(6, "退货成功"),
    ;
    private final int value;

    private final String name;

    OrderReturnStatusEnum(int value, String name) {
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
