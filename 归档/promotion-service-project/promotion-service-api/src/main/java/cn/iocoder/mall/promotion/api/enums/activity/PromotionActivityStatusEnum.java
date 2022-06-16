package cn.iocoder.mall.promotion.api.enums.activity;

/**
 * 促销活动状态枚举
 */
public enum PromotionActivityStatusEnum {

    WAIT(10, "未开始"),
    RUN(20, "进行中"),
    END(30, "已结束"),
    /**
     * 1. WAIT、RUN、END 可以转换成 INVALID 状态。
     * 2. INVALID 只可以转换成 DELETED 状态。
     */
    INVALID(40, "已撤销"),
    DELETED(50, "已删除"),
    ;

    /**
     * 状态值
     */
    private final Integer value;
    /**
     * 状态名
     */
    private final String name;

    PromotionActivityStatusEnum(Integer value, String name) {
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
