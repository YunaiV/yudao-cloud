package cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.aftersale.AfterSaleOrderDO;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderAfterSaleStatusEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderStatusEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 交易订单的商品项，主要是商品信息等等
 *
 * idx_orderId 索引：基于 {@link #orderId} 字段
 *
 * @author Sin
 * @time 2019-03-16 14:03
 */
@TableName(value = "trade_order_item")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TradeOrderItemDO extends DeletableDO {

    // ========== 订单项基本信息 ==========
    /**
     * 编号
     */
    private Integer id;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 订单项状态
     *
     * 枚举 {@link TradeOrderStatusEnum}
     */
    private Integer status;

    // ========== 商品基本信息 ==========
    /**
     * 商品 SPU 编号
     */
    private Integer spuId;
    /**
     * 商品 SKU 编号
     */
    private Integer skuId;
    /**
     * 商品名称
     */
    private String skuName;
    /**
     * 商品图片
     */
    private String skuImage;
    /**
     * 购买数量
     */
    private Integer quantity;

    // ========== 价格 + 支付基本信息 ==========
    /**
     * 原始单价，单位：分。
     */
    private Integer originPrice;
    /**
     * 购买单价，单位：分
     */
    private Integer buyPrice;
    /**
     * 最终单价，单位：分。
     */
    private Integer presentPrice;
    /**
     * 购买总金额，单位：分
     *
     * 用途类似 {@link #presentTotal}
     */
    private Integer buyTotal;
    /**
     * 优惠总金额，单位：分。
     */
    private Integer discountTotal;
    /**
     * 最终总金额，单位：分。
     *
     * 注意，presentPrice * quantity 不一定等于 presentTotal 。
     * 因为，存在无法整除的情况。
     * 举个例子，presentPrice = 8.33 ，quantity = 3 的情况，presentTotal 有可能是 24.99 ，也可能是 25 。
     * 所以，需要存储一个该字段。
     */
    private Integer presentTotal;
    // 如上字段，举个例子：
    // 假设购买三个，即 quantity = 3 。
    // originPrice = 15
    // 使用限时折扣（单品优惠）8 折，buyPrice = 12
    // 开始算总的价格
    // buyTotal = buyPrice * quantity = 12 * 3 = 36
    // discountTotal ，假设有满减送（分组优惠）满 20 减 10 ，并且使用优惠劵满 1.01 减 1 ，则 discountTotal = 10 + 1 = 11
    // presentTotal = buyTotal - discountTotal = 24 - 11 = 13
    // 最终 presentPrice = presentTotal / quantity = 13 / 3 = 4.33
    /**
     * 退款总金额，单位：分
     */
    private Integer refundTotal;
    // TODO 芋艿：退款单和这个的关联。例如说，一个子单可以退款多次；需要额外新增表

    // ========== 物流基本信息 ==========
    /**
     * 物流编号
     *
     * 外键 {@link TradeOrderLogisticsDO#getId()}
     */
    private Integer logisticsId;

    // ========== 售后基本信息 ==========
    /**
     * 售后状态
     *
     * 枚举 {@link TradeOrderAfterSaleStatusEnum}
     */
    private Integer afterSaleStatus;
    /**
     * 售后订单编号
     *
     * 外键 {@link AfterSaleOrderDO#getId()}
     */
    private Integer afterSaleOrderId;

}
