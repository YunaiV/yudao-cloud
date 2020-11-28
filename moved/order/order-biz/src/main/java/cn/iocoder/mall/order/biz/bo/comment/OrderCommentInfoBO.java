package cn.iocoder.mall.order.biz.bo.comment;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 订单回复评价详情
 *
 * @author wtz
 * @time 2019-05-16 18:40
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentInfoBO implements Serializable {

    /**
     * 评论 id
     */
    private Integer id;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户昵称
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
     * 点赞数
     */
    private Integer likeCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 商品 sku id
     */
    private int productSkuId;

    /**
     * 商品 sku 属性
     */
    private String productSkuAttrs;

    /**
     * 商品 sku 价格
     */
    private String productSkuPrice;

    /**
     * 商品 sku 地址
     */
    private String productSkuPicUrl;


}
