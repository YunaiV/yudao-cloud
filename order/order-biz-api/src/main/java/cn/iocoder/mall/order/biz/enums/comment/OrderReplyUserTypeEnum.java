package cn.iocoder.mall.order.biz.enums.comment;

/**
 *
 * 评论回复 - 回复的用户的类型
 *
 * @author wtz
 * @time 2019-05-19 15:19
 */
public enum  OrderReplyUserTypeEnum {

    USER(0, "普通用户"),
    MERCHANT(1, "商家");
    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    OrderReplyUserTypeEnum(Integer value, String name) {
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
