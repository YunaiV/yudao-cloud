package cn.iocoder.mall.promotion.api.enums.activity;

/**
 * 促销活动类型枚举
 */
public enum PromotionActivityTypeEnum {

    TIME_LIMITED_DISCOUNT(1, "限时折扣"),
    FULL_PRIVILEGE(2, "满减送"),
    ;

    /**
     * 类型值
     */
    private final Integer value;
    /**
     * 类型名
     */
    private final String name;

    PromotionActivityTypeEnum(Integer value, String name) {
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
