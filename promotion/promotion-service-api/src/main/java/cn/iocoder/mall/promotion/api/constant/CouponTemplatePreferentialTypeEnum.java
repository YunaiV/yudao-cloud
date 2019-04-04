package cn.iocoder.mall.promotion.api.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

public enum CouponTemplatePreferentialTypeEnum implements IntArrayValuable {

    PRICE(1, "代金卷"),
    DISCOUNT(2, "折扣卷"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponTemplatePreferentialTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponTemplatePreferentialTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    @Override
    public int[] array() {
        return ARRAYS;
    }

}
