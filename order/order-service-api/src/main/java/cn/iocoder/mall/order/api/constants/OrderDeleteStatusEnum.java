package cn.iocoder.mall.order.api.constants;

/**
 * 订单 deleteStatus
 *
 * @author Sin
 * @time 2019-03-17 20:58
 */
public enum OrderDeleteStatusEnum {

    DELETE_NO(0, "正常"),
    DELETE_YES(1, "已删除")
    ;

    private final int value;

    private final String name;

    OrderDeleteStatusEnum(int value, String name) {
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
