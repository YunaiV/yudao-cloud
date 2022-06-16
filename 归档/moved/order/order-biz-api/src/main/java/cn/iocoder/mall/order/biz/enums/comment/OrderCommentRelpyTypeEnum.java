package cn.iocoder.mall.order.biz.enums.comment;

/**
 *
 * 评论回复类型
 *
 * @author wtz
 * @time 2019-06-01 10:30:00
 */
public enum OrderCommentRelpyTypeEnum {

    REPLY_REPLY(0, "回复的回复"),
    COMMENT_REPLY(1, "评论的回复");
    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    OrderCommentRelpyTypeEnum(Integer value, String name) {
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
