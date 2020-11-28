package cn.iocoder.mall.order.biz.bo.comment;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *
 * 评论详情和商家评论回复
 *
 * @author wtz
 * @time 2019-06-03 20:30
 */
@Data
@Accessors(chain = true)
public class OrderCommentInfoAndMerchantReplyBO {

    /**
     * 评论详情
     */
    private OrderCommentInfoBO orderCommentInfoBO;

    /**
     * 商家评论回复
     */
    private List<OrderCommentMerchantReplyBO> orderCommentMerchantReplyBOS;

}
