package cn.iocoder.mall.promotion.api.enums.coupon.card;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 优惠劵状态枚举
 */
public enum CouponCardStatusEnum implements IntArrayValuable {

    UNUSED(1, "未使用"),
    USED(2, "已使用"),
    EXPIRE(3, "已过期"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponCardStatusEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponCardStatusEnum(Integer value, String name) {
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
