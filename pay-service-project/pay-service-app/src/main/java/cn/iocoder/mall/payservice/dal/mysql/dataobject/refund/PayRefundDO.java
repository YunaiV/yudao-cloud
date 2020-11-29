package cn.iocoder.mall.payservice.dal.mysql.dataobject.refund;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import cn.iocoder.mall.payservice.enums.refund.PayRefundStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 退款单 DO
 */
@TableName("pay_refund")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayRefundDO extends DeletableDO {

    /**
     * 编号，自增
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 支付交易编号
     */
    private Integer transactionId;
    /**
     * 生成传输给第三方的退款号
     *
     * 唯一索引
     */
    private String refundCode;
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
     * 业务线的订单编号
     *
     * 1. 使用 String 的原因是，业务线可能使用 String 做为编号
     * 2. 每个 appId 下，orderId 唯一
     */
    private String orderId;
    /**
     * 发起交易的 IP
     */
    private String createIp;
    /**
     * 业务退款描述
     */
    private String orderDescription;
    /**
     * 退款金额，单位：分。
     *
     * TODO 暂时不考虑货币类型。
     */
    private Integer price;
    /**
     * 退款状态
     *
     * 外键 {@link PayRefundStatus}
     */
    private Integer status;
    /**
     * 回调业务线完成时间
     */
    private Date finishTime;
    /**
     * 异步通知地址
     */
    private String notifyUrl;
    /**
     * 扩展内容
     *
     * 异步通知的时候填充回调的数据
     */
    private String extensionData;
    /**
     * 退款渠道
     */
    private Integer refundChannel;
    /**
     * 第三方退款成功的时间
     */
    private Date refundTime;
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
