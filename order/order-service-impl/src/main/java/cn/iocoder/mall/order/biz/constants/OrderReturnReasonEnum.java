package cn.iocoder.mall.order.biz.constants;

/**
 * 订单退货原因
 *
 * @author Sin
 * @time 2019-03-20 21:17
 */
public enum OrderReturnReasonEnum {

    ///
    /// 未发货情况

    REASON_000(0, "其他"),
    REASON_001(1, "拍错/勿拍/多拍"),
    REASON_002(2, "缺货"),

    ///
    /// 已发货情况

    REASON_020(20, "七天无理由"),
    REASON_021(21, "质量问题"),
    REASON_022(22, "不想要了"),

    ;

    // TODO: 2019-03-20 Sin  已发货情况 补全，需要对照一下 淘宝

    private Integer value;

    private String name;

    OrderReturnReasonEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderCommonReasonEnum{" +
                "value=" + value +
                ", name=" + name +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }}
