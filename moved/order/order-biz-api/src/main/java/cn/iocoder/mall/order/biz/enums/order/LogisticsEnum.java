package cn.iocoder.mall.order.biz.enums.order;

/**
 * 物流信息
 *
 * @author Sin
 * @time 2019-03-30 22:33
 */
public enum LogisticsEnum {

    LOGISTICS_1(1, "顺丰快递"),
    LOGISTICS_2(2, "圆通快递"),
    LOGISTICS_3(3, "申通快递"),
    LOGISTICS_4(4, "韵答快递"),
    LOGISTICS_5(5, "天天快递"),
    LOGISTICS_6(6, "EMS中国邮政"),

    ;

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    LogisticsEnum(int value, String name) {
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
