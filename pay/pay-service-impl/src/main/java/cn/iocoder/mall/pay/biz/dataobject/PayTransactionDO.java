package cn.iocoder.mall.pay.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 支付交易 DO
 */
@Data
@Accessors(chain = true)
public class PayTransactionDO extends DeletableDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 应用编号
     *
     * 不同业务线分配不同的 appId
     * 举个例子，
     * 1. 电商系统的订单，appId = 1024
     * 2. 活动系统的订单，appId = 2048
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
     *
     * TODO 暂时不考虑货币类型。
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


    // TODO return url
    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 成功支付的交易拓展编号
     *
     * @see PayTransactionExtensionDO#getId()
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

}
