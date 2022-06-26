package cn.iocoder.mall.order.biz.constants;

/**
 * 发货类型/发货方式
 *
 * @author Sin
 * @time 2019-04-05 16:03
 */
public enum OrderDeliveryTypeEnum {

    NONE(1, "未选择"),
    ORDER_ONLINE(2, "快递"),
    CONTACT_YOURSELF(3, "自己联系"),
    NO_DELIVERY(4, "无物流信息"),
    ;

    private Integer value;

    private String name;

    OrderDeliveryTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "OrderRecipientTypeEnum{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
