package cn.iocoder.mall.tradeservice.rpc.order.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 交易订单项 Response DTO
 */
@Data
@Accessors(chain = true)
public class TradeOrderItemRespDTO implements Serializable {

    /**
     * id自增长
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单项状态
     */
    private Integer status;
    /**
     * 商品 SKU 编号
     */
    private Integer skuId;
    /**
     * 商品 SPU 编号
     */
    private Integer spuId;
    /**
     * 商品名字
     */
    private String skuName;
    /**
     * 图片名字
     */
    private String skuImage;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 原始单价，单位：分
     */
    private Integer originPrice;
    /**
     * 购买单价，单位：分
     */
    private Integer buyPrice;
    /**
     * 最终价格，单位：分
     */
    private Integer presentPrice;
    /**
     * 购买总金额，单位：分
     */
    private Integer buyTotal;
    /**
     * 优惠总金额，单位：分
     */
    private Integer discountTotal;
    /**
     * 最终总金额，单位：分
     */
    private Integer presentTotal;
    /**
     * 退款总金额，单位：分
     */
    private Integer refundTotal;
    /**
     * 物流id
     */
    private Integer logisticsId;
    /**
     * 售后状态
     */
    private Integer afterSaleStatus;
    /**
     * 售后订单编号
     */
    private Integer afterSaleOrderId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除状态
     */
    private Integer deleted;

}
