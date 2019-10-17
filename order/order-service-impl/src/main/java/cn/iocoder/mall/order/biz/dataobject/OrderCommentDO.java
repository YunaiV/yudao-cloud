package cn.iocoder.mall.order.biz.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单评论表
 *
 * @author  wtz
 * @time 2019-05-14 20:48
 *
 */
@Data
@Accessors(chain = true)
@TableName(value = "order_comment")
public class OrderCommentDO extends BaseDO {

    /**
     * 评论 id // TODO FROM 芋艿 TO wtz 中英文之间，要有空格
     */
    private Integer id;

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
     * 用户id
     */
    private Integer userId;

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
     * 商品描述
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
     * 回复条数
     */
    private Integer replayCount;

    /**
     * 点赞数 // TODO FROM 芋艿 TO wtz collect 是收藏的意思，最好换个单词噢。
     */
    private Integer likeCount;

    /**
     * 评论的内容
     */
    private String commentContent;

    /**
     * 评论的图片地址
     */
    private String commentPics;

    /**
     * 订单评论状态
     */
    private Integer commentState;

}
