package cn.iocoder.mall.order.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 订单评论信息详情 query
 *
 * @author wtz
 * @time 2019-05-19 10:16
 */
@Data
@Accessors(chain = true)
public class OrderCommentReplyPageDTO implements Serializable {

    /**
     * 评论 id
     */
    private Integer commentId;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页条数
     */
    private Integer pageSize;

}
