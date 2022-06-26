package cn.iocoder.mall.order.biz.dto.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 订单评论状态分页信息 query
 *
 * @author wtz
 * @time 2019-06-07 10:45
 */
@Data
@Accessors(chain = true)
public class OrderCommentStateInfoPageDTO implements Serializable {

    /**
     * 用户 id
     */
    private Integer userId;

    /**
     * 评论状态
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
