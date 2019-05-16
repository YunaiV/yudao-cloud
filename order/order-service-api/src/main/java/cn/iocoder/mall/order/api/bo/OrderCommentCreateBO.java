package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;


/**
 *
 * 订单评论创建
 *
 * @author wtz
 * @time 2019-05-15 20:35
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentCreateBO {

    /**
     * 评论id
     */
    private Integer id;
}
