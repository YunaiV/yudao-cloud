package cn.iocoder.mall.promotion.api.enums.coupon.card;

import cn.iocoder.common.framework.core.IntArrayValuable;

import java.util.Arrays;

/**
 * 优惠劵领取方式
 */
public enum CouponCardTakeTypeEnum implements IntArrayValuable {

    BY_USER(1, "用户主动领取"),
    BY_ADMIN(2, "已使用"),
    ;

    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(CouponCardTakeTypeEnum::getValue).toArray();

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    CouponCardTakeTypeEnum(Integer value, String name) {
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
