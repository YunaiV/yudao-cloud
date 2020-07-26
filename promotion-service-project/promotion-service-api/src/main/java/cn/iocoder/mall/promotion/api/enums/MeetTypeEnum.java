package cn.iocoder.mall.promotion.api.enums;

/**
 * 匹配类型枚举
 */
public enum MeetTypeEnum {

    PRICE(1, "金额"),
    QUANTITY(2, "数量"),;

    /**
     * 值
     */
    private final Integer value;
    /**
     * 名字
     */
    private final String name;

    MeetTypeEnum(Integer value, String name) {
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
