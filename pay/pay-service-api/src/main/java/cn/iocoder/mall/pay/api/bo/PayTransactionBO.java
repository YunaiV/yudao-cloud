package cn.iocoder.mall.pay.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 支付交易 BO
 */
@Data
@Accessors(chain = true)
public class PayTransactionBO implements Serializable {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 应用编号
     */
    private String appId;
    /**
     * 发起交易的 IP
     */
    private String createIp;
    /**
     * 业务线的订单编号
     *
     * 1. 使用 String 的原因是，业务线可能使用 String 做为编号
     * 2. 每个 appId 下，orderId 唯一
     */
    private String orderId;
    /**
     * 订单商品名
     */
    private String orderSubject;
    /**
     * 订单商品描述
     */
    private String orderDescription;
    /**
     * 订单备注
     */
    private String orderMemo;
    /**
     * 支付金额，单位：分。
     */
    private Integer price;
    /**
     * 订单状态
     *
     * @see cn.iocoder.mall.pay.api.constant.PayTransactionStatusEnum
     */
    private Integer status;
    /**
     * 交易过期时间
     */
    private Date expireTime;
    /**
     * 回调业务线完成时间
     */
    private Date finishTime;

    /**
     * 成功支付的交易拓展编号
     */
    private Integer extensionId;
    /**
     * 支付成功的支付渠道
     *
     * @see cn.iocoder.mall.pay.api.constant.PayChannelEnum
     */
    private Integer payChannel;
    /**
     * 第三方支付成功的时间
     */
    private Date paymentTime;
    /**
     * 收到第三方系统通知的时间
     *
     * 一般情况下，即第三方系统的异步通知
     */
    private Date notifyTime;
    /**
     * 第三方的流水号
     */
    private String tradeNo;
    /**
     * 创建时间
     */
    private Date createTime;

    // ========== 退款相关 ==========

    /**
     * 退款总金额
     */
    private Integer refundTotal;

}
