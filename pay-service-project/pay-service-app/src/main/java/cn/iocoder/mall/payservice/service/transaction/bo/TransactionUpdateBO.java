package cn.iocoder.mall.payservice.service.transaction.bo;

import lombok.*;
import lombok.experimental.*;
import io.swagger.annotations.*;
import java.util.*;
import javax.validation.constraints.*;

/**
* pay_transaction更新 BO
*/
@Data
@Accessors(chain = true)
public class TransactionUpdateBO {

    /**
     * 编号，自增
     */
    @NotNull(message = "编号，自增不能为空")
    private Integer id;
    /**
     * 应用编号
     */
    @NotEmpty(message = "应用编号不能为空")
    private String appId;
    /**
     * 发起交易的 IP
     */
    @NotEmpty(message = "发起交易的 IP不能为空")
    private String createIp;
    /**
     * 业务线的订单编号
     */
    @NotEmpty(message = "业务线的订单编号不能为空")
    private String orderId;
    /**
     * 订单商品名
     */
    @NotEmpty(message = "订单商品名不能为空")
    private String orderSubject;
    /**
     * 订单商品描述
     */
    @NotEmpty(message = "订单商品描述不能为空")
    private String orderDescription;
    /**
     * 订单备注
     */
    private String orderMemo;
    /**
     * 支付金额，单位：分。
     */
    @NotNull(message = "支付金额，单位：分。不能为空")
    private Integer price;
    /**
     * 订单状态
     */
    @NotNull(message = "订单状态不能为空")
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
    @NotEmpty(message = "异步通知地址不能为空")
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
    /**
     * 退款总金额
     */
    private Integer refundTotal;

}