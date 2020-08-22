package cn.iocoder.mall.promotion.api.enums.coupon.template;

import java.util.Arrays;

/**
 * 优惠劵（码）模板的类型的枚举
 */
public enum CouponTemplateTypeEnum {

    CARD(1, "优惠劵"),
    CODE(2, "折扣卷"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponTemplateTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponTemplateTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
