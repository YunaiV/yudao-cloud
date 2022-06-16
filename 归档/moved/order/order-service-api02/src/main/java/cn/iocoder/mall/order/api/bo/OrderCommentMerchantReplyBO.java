package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 商家评论回复
 *
 * @author wtz
 * @time 2019-06-03 19:30
 */
@Data
@Accessors(chain = true)
public class OrderCommentMerchantReplyBO {

    /**
     * 商家评论回复
     */
    private String replyContent;
}
