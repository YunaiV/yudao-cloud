package cn.iocoder.mall.tradeservice.rpc.cart.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 购物车的商品信息 Response DTO
 */
@Data
@Accessors(chain = true)
public class CartItemRespDTO implements Serializable {

    // ========= 基础字段 BEGIN =========

    /**
     * 编号，唯一自增。
     */
    private Integer id;
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

    // ========= 商品信息 END =========

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

}
