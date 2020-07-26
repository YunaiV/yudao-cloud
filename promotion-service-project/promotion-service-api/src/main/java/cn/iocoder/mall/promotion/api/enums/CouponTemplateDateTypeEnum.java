package cn.iocoder.mall.promotion.api.enums;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

public enum CouponTemplateDateTypeEnum implements IntArrayValuable {

    FIXED_DATE(1, "固定日期"),
    FIXED_TERM(2, "领取日期"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponTemplateDateTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponTemplateDateTypeEnum(Integer value, String name) {
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
