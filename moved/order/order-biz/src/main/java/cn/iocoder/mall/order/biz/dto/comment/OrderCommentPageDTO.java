package cn.iocoder.mall.order.biz.dto.comment;

import cn.iocoder.common.framework.vo.PageParam;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单评论 page
 *
 * @author xiaofeng
 */
@Data
@Accessors(chain = true)
public class OrderCommentPageDTO extends PageParam implements Serializable {

    /**
     * 商品 sku id
     */
    private Integer productSkuId;

}
