package cn.iocoder.mall.order.biz.enums.order;

/**
 * 订单换货原因
 *
 * @author Sin
 * @time 2019-03-20 21:17
 */
public enum OrderExchangeReasonEnum {

    REASON_000(0, "其他"),
    REASON_001(1, "尺码不合适"),
    REASON_002(2, "质量问题"),
    REASON_003(3, "不喜欢"),
    ;

    private Integer value;

    private String name;

    OrderExchangeReasonEnum(Integer value, String name) {
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
