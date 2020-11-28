package cn.iocoder.mall.order.biz.bo.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 订单评论创建
 *
 * @author wtz
 * @time 2019-05-19 18:32
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentCreateBO implements Serializable {

    /**
     * 订单评论 id
     */
    private Integer id;

}
