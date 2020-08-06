package cn.iocoder.mall.order.api.constant;

/**
 * 订单评论状态
 *
 * @author wtz
 * @time 2019-06-15 14:26
 */
public enum OrderCommentStatusEnum {

    WAIT_COMMENT(0, "待评论"),
    SUCCESS_COMMENT(1, "评论成功");
    /**
     * 状态值
     */
    private Integer value;
    /**
     * 状态名
     */
    private String name;

    OrderCommentStatusEnum(Integer value, String name) {
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
