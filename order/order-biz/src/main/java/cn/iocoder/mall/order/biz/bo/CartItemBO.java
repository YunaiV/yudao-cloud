package cn.iocoder.mall.order.biz.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 购物车的商品信息 DO
 */
@Data
@Accessors(chain = true)
public class CartItemBO {

    // ========= 基础字段 BEGIN =========

    /**
     * 编号，唯一自增。
     */
    private Integer id;
    /**
     * 状态
     *
     * 1-正常
     * 2-主动删除
     * 3-下单删除
     */
    private Integer status;
    /**
     * 是否选中
     */
    private Boolean selected;

    // ========= 基础字段 END =========

    // ========= 买家信息 BEGIN =========

    /**
     * 用户编号
     */
    private Integer userId;
//    /**
//     * 会话 key
//     */
//    private String nobody;

    // ========= 买家信息 END =========

    // ========= 商品信息 BEGIN =========

    /**
     * 商品 SPU 编号
     */
    private Integer spuId;
    /**
     * 商品 SKU 编号
     */
    private Integer skuId;
    /**
     * 商品购买数量
     */
    private Integer quantity;

    // TODO 冗余字段


    // ========= 商品信息 END =========

    // ========= 交易信息 BEGIN =========

    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单创建时间
     */
    private Date orderCreateTime;

    // ========= 交易信息 BEGIN =========

    // ========= 优惠信息 BEGIN =========

//    /**
//     * 商品营销活动编号
//     */
//    private Integer activityId;
//    /**
//     * 商品营销活动类型
//     */
//    private Integer activityType;

    // ========= 优惠信息 END =========

    /**
     * 创建时间
     */
    private Date createTime;

}
