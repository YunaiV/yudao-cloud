package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * 订单评论状态详情分页(这么设计因为这个接口需要登陆以后才能看到所以与订单分页接口分开)
 *
 * @author wtz
 * @time 2019-06-07 10:39
 */
@Data
@Accessors(chain = true)
public class OrderCommentStateInfoPageBO implements Serializable {

    /**
     * (待/已)评论总数
     */
    private Integer total;

    /**
     * 评论状态
     */
    private List<OrderCommentStateInfoItem> orderCommentStateInfoItems;

    @Data
    @Accessors(chain = true)
    public static class OrderCommentStateInfoItem{

        /**
         * 订单 id
         */
        private Integer orderId;

        /**
         * 订单编号
         */
        private String orderNo;

        /**
         * 商品 id
         */
        private Integer productSpuId;

        /**
         * 商品名称
         */
        private String productSpuName;

        /**
         * 商品 sku id
         */
        private Integer productSkuId;

        /**
         * 商品 sku 属性
         */
        private String productSkuAttrs;

        /**
         * 商品 sku 价格
         */
        private Integer productSkuPrice;

        /**
         * 商品 sku url
         */
        private String productSkuPicUrl;

        /**
         * 订单评论状态
         */
        private Integer commentState;

        /**
         * 创建时间
         */
        private Date createTime;
    }


}
