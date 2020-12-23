package cn.iocoder.mall.tradeservice.dal.mysql.dataobject.aftersale;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderDO;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderItemDO;
import cn.iocoder.mall.tradeservice.enums.aftersale.AfterSaleTypeEnum;
import cn.iocoder.mall.tradeservice.enums.aftersale.AfterSaleWayEnum;
import cn.iocoder.mall.tradeservice.enums.logistics.LogisticsDeliveryTypeEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 售后订单，用于处理 {@link TradeOrderDO} 交易订单的退货换流程
 *
 * TODO 超时机制
 * TODO 用户（买家）和商家每一个步骤，都需要记录协商记录，多设计一个表
 */
@TableName(value = "after_sale_order")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AfterSaleOrderDO extends DeletableDO {

    /**
     * 售后订单编号
     */
    private Integer id;
    /**
     * 售后状态
     */
    private Integer status;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户手机
     */
    private String userMobile;
    /**
     * 售后类型
     *
     * 枚举 {@link AfterSaleTypeEnum}
     */
    private Integer type;
    /**
     * 售后方式
     *
     * 枚举 {@link AfterSaleWayEnum}
     */
    private Integer way;
    /**
     * 货物状态，是否收到货
     */
    private Boolean logisticsReceived;
    /**
     * 用户售后原因
     *
     * TODO 不同情况下的退款，原因不同
     */
    private Integer reasonType;
    /**
     * 用户售后说明
     */
    private String reasonMemo;
    /**
     * 用户售后凭证图片的地址数组
     *
     * 数组，以逗号分隔
     */
    private String reasonPicUrls;
    /**
     * 商家拒绝理由
     */
    private String rejectReasonMemo;

    // ========== 交易订单相关 ==========
    /**
     * 交易订单编号
     *
     * 外键 {@link TradeOrderDO#getId()}
     */
    private Integer tradeOrderId;
    /**
     * 交易订单项编号
     *
     * 外键 {@link TradeOrderItemDO#getId()}
     */
    private Integer tradeOrderItemId;
    /**
     * 商品 SKU 编号
     */
    private Integer skuId;
    /**
     * 商品数量
     */
    private Integer quantity;

    // ========== 退款相关 ==========
    /**
     * 退款金额，单位：分。
     */
    private Integer refundPrice;

    // ========== 退货相关 ==========
    /**
     * 退货地址，即商家的收件地址
     */
    private String returnDetailAddress;
    /**
     * 退货物流公司编号
     *
     * 使用 DataDict 数据字典 EXPRESS
     */
    private String returnLogisticsExpressId;
    /**
     * 退货物流单号
     */
    private String returnLogisticsExpressNo;
    /**
     * 退货物流说明
     */
    private String returnLogisticsReasonMemo;
    /**
     * 退货物流凭证图片的地址数组
     *
     * 数组，以逗号分隔
     */
    private String returnLogisticsPicUrls;
    /**
     * 退货物流的填写时间
     */
    private Date returnLogisticsDate;

    // ========== 换货相关 ==========
    /**
     * 换货物流的配送方式
     * 这里指的是商家重新给用户（买家）发货
     *
     * 枚举 {@link LogisticsDeliveryTypeEnum}
     */
    private Integer exchangeLogisticsDeliveryType;
    /**
     * 换货物流公司编号
     */
    private Integer exchangeLogisticsExpressId;
    /**
     * 换货物流公司单号
     */
    private String exchangeLogisticsExpressNo;
    /**
     * 换货物流的填写时间
     */
    private Date exchangeLogisticsDate;

}
