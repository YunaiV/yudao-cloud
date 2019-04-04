package cn.iocoder.mall.promotion.api.constant;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

public enum CouponTemplateRangeTypeEnum implements IntArrayValuable {

    ALL(10, "所有可用"),
    PRODUCT_INCLUDE_PRT(20, "部分商品可用，或指定商品可用"),
    PRODUCT_EXCLUDE_PRT(21, "部分商品不可用，或指定商品可用"),
    CATEGORY_INCLUDE_PRT(30, "部分分类可用，或指定分类可用"),
    CATEGORY_EXCLUDE_PRT(31, "部分分类不可用，或指定分类可用"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponTemplateRangeTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponTemplateRangeTypeEnum(Integer value, String name) {
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
