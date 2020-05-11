package cn.iocoder.mall.order.biz.enums.cart;

import java.util.Arrays;

public enum CartItemStatusEnum {

    ENABLE(1, "正常"),
    DELETE_BY_MANUAL(2, "主动删除"),
    DELETE_BY_ORDER(3, "下单删除"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CartItemStatusEnum::getValue).toArray();

    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    CartItemStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public CartItemStatusEnum setValue(Integer value) {
        this.value = value;
        return this;
    }

    public String getName() {
        return name;
    }

    public CartItemStatusEnum setName(String name) {
        this.name = name;
        return this;
    }

}
