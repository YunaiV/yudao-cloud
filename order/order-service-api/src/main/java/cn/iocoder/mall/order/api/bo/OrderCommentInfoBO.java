package cn.iocoder.mall.order.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 *
 * 订单回复评价
 *
 * @author wtz
 * @time 2019-05-16 18:40
 *
 */
public class OrderCommentInfoBO {

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户的真实姓名
     */
    private String userNickName;

    /**
     * 评价星
     */
    private Integer star;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 评论的图片地址
     */
    private String commentPics;

    /**
     * 回复条数
     */
    private Integer replayCount;

    /**
     * 点赞数
     */
    private Integer collectCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品SKUid
     */
    private int productSkuId;

    /**
     * 商品SKU属性
     */
    private String productSkuAttrs;

    /**
     * 商品SKU价格
     */
    private String productSkuPrice;

    /**
     * 商品SKU地址
     */
    private String productSkuPicUrl;


    /**
     * 商家回复
     */
    List<OrderCommentReplayMerchantItem> orderCommentReplayMerchantItems;

    /**
     * 用户回复
     */
    List<OrderCommentReplayUserItem> orderCommentReplayUserItems;

    @Data
    @Accessors(chain = true)
    private static class OrderCommentReplayMerchantItem{
        /**
         * 回复的内容
         */
        private String replyContent;
    }



    @Data
    @Accessors(chain = true)
    private static class OrderCommentReplayUserItem{
        /**
         * 回复id
         */
        private Integer id;

        /**
         * 回复的类型
         */
        private Integer replyType;

        /**
         * 回复的内容
         */
        private String replyContent;

        /**
         * 回复的用户id
         */
        private int replyUserId;

        /**
         * 回复用户的真实姓名
         */
        private String replyUserNickName;

        /**
         * 回复用户的头像
         */
        private String replyUserAvatar;

        /**
         * 回复的点赞数
         */
        private int replyCollectCount;

        /**
         * 回复目标用户昵称
         */
        private String parentUserNickName;

        /**
         * 创建时间
         */
        private Date createTime;

    }



}
