package cn.iocoder.mall.promotion.api.enums;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 优惠类型枚举
 */
public enum PreferentialTypeEnum implements IntArrayValuable {

    PRICE(1, "减价"),
    DISCOUNT(2, "打折"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(PreferentialTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    PreferentialTypeEnum(Integer value, String name) {
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
