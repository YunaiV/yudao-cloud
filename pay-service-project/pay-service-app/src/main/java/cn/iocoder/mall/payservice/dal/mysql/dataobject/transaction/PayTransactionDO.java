package cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 支付交易表
*/
@TableName("pay_transaction")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayTransactionDO extends DeletableDO {

    /**
     * 编号，自增
     */
    @TableId
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
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
     * 异步通知地址
     */
    private String notifyUrl;
    /**
     * 成功支付的交易拓展编号
     */
    private Integer extensionId;
    /**
     * 支付成功的支付渠道
     */
    private Integer payChannel;

    /**
     * 第三方支付成功的时间
     */
    private Date paymentTime;
    /**
     * 收到第三方系统通知的时间
     */
    private Date notifyTime;
    /**
     * 第三方的流水号
     */
    private String tradeNo;

    // ========== 退款相关 ==========

    /**
     * 退款总金额
     */
    private Integer refundTotal;

}
