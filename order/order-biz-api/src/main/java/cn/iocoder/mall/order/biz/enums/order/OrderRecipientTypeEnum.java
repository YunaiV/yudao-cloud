package cn.iocoder.mall.order.biz.enums.order;

/**
 * 订单收件信息 type（配送信息）
 *
 * @author Sin
 * @time 2019-04-05 16:03
 */
public enum OrderRecipientTypeEnum {

    EXPRESS(1, "快递")

    ;

    private Integer value;

    private String name;

    OrderRecipientTypeEnum(Integer value, String name) {
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
