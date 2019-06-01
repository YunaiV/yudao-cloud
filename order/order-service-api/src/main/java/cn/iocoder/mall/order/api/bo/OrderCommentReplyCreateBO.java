package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * 订单回复创建
 *
 * @author wtz
 * @time 2019-05-19 18:35
 */
@Data
@Accessors(chain = true)
public class OrderCommentReplyCreateBO implements Serializable {


    /**
     * 评论回复 id
     */
    private Integer id;
}
