package cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.tradeservice.enums.logistics.LogisticsDeliveryTypeEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderAfterSaleStatusEnum;
import cn.iocoder.mall.tradeservice.enums.order.TradeOrderStatusEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 交易订单
 *
 * idx_userId 索引：基于 {@link #userId} 字段
 *
 * @author Sin
 * @time 2019-03-16 13:49
 */
@TableName(value = "trade_order")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TradeOrderDO extends DeletableDO {

    // ========== 订单基本信息 ==========
    /**
     * 订单编号（主键）
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 订单编号（业务）
     */
    private String orderNo;
    /**
     * 订单状态。
     * 如果有多个商品分开发货，需要全部商品发完才会改变状态
     *
     * 枚举 {@link TradeOrderStatusEnum}
     */
    private Integer orderStatus;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单结束时间
     *
     * 即交易订单状态变成 {@link TradeOrderStatusEnum#COMPLETED} 和 {@link TradeOrderStatusEnum#CLOSED} 的时间
     */
    private Date endTime;

    // ========== 价格 + 支付基本信息 ==========
    /**
     * 购买（商品）总金额，单位：分
     */
    private Integer buyPrice;
    /**
     * 优惠总金额，单位：分。
     */
    private Integer discountPrice;
    /**
     * 物流金额 (分)
     */
    private Integer logisticsPrice;
    /**
     * 最终金额，单位：分
     *
     * buyPrice + logisticsPrice -  discountPrice = presentPrice
     */
    private Integer presentPrice;
    /**
     * 实际已支付金额，单位：分
     *
     * 初始时，金额为 0 。等到支付成功后，会进行更新。
     */
    private Integer payPrice;
    /**
     * 退款金额，单位：分
     *
     * 注意，退款并不会影响 {@link #payPrice} 实际支付金额
     * 也就说，一个订单最终产生多少金额的收入 = payPrice - refundPrice
     */
    private Integer refundPrice;
    /**
     * 付款时间
     */
    private Date payTime;
    /**
     * 支付订单编号
     *
     * 对接 pay-service 支付服务的支付订单编号
     */
    private Integer payTransactionId;
    /**
     * 支付成功的支付渠道
     */
    private Integer payChannel;

    // ========== 收件 + 物流基本信息 ==========
    /**
     * 配送类型
     *
     * 枚举 {@link LogisticsDeliveryTypeEnum}
     */
    private Integer deliveryType;
    /**
     * 发货时间
     */
    private Date deliveryTime;
    /**
     * 收货时间
     */
    private Date receiveTime;
    /**
     * 收件人名称
     */
    private String receiverName;
    /**
     * 收件人手机
     */
    private String receiverMobile;
    /**
     * 收件人地区编码
     */
    private Integer receiverAreaCode;
    /**
     * 收件人详细地址
     */
    private String receiverDetailAddress;

    // ========== 售后基本信息 ==========
    /**
     * 售后状态
     *
     * 枚举 {@link TradeOrderAfterSaleStatusEnum}
     */
    private Integer afterSaleStatus;

    // ========== 营销基本信息 ==========
    /**
     * 优惠劵编号
     */
    private Integer couponCardId;

    // TODO 芋艿，这块还要结合营销和价格计算，在去优化下。

}
