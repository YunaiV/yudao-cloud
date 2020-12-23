package cn.iocoder.mall.order.biz.dto.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单评论超时
 *
 * @author wtz
 * @time 2019-06-15 10:59
 */
@Data
@Accessors(chain = true)
public class OrderCommentTimeOutPageDTO implements Serializable {
    /**
     * 超过的天数
     */
    private Integer overDay;

    /**
     * 评论的状态
     */
    private Integer commentState;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;
}
