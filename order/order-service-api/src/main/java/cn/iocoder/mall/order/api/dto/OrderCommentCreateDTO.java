package cn.iocoder.mall.order.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 订单评论创建
 *
 * @author wtz
 * @time 2019-05-15 20:42
 *
 */
@Data
@Accessors(chain = true)
public class OrderCommentCreateDTO implements Serializable {

    /**
     * 订单id
     */
    private int orderId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 商品SPU id
     */
    private int productSpuId;

    /**
     * 商品SPU 名字 SPU 这两个属性待考量我认为加入进去以后后期一些分析可能好做一些
     */
    private String productSpuName;

    /**
     * 商品SKU id
     */
    private int productSkuId;

    /**
     * 商品SKU属性
     */
    private String productSkuAttrs;

    /**
     * 商品SKU价格
     */
    private int productSkuPrice;

    /**
     * 商品SKU地址
     */
    private String productSkuPicUrl;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 星
     */
    private Integer star;

    /**
     * 产品描述
     */
    private Integer productDescriptionStar;

    /**
     * 物流评价
     */
    private Integer logisticsStar;

    /**
     * 商家评价
     */
    private Integer merchantStar;

    /**
     * 评论内容
     */
    private String commentContent;
}
