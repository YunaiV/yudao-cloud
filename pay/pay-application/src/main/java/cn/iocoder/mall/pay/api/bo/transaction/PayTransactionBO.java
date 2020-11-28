package cn.iocoder.mall.pay.api.bo.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel("支付交易 BO")
@Data
@Accessors(chain = true)
public class PayTransactionBO implements Serializable {

    @ApiModelProperty(value = "交易编号", required = true, example = "POd4RC6a")
    private Integer id;

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    private String appId;

    @ApiModelProperty(value = "发起交易的 IP", required = true, example = "192.168.10.1")
    private String createIp;

    @ApiModelProperty(value = "订单号不能为空", required = true, example = "1024")
    private String orderId;

    @ApiModelProperty(value = "商品名", required = true, example = "芋道源码")
    private String orderSubject;

    @ApiModelProperty(value = "订单商品描述", required = true, example = "绵啾啾的")
    private String orderDescription;

    @ApiModelProperty(value = "订单商品备注", example = "绵啾啾的")
    private String orderMemo;

    @ApiModelProperty(value = "支付金额，单位：分。", required = true, example = "10")
    private Integer price;

    @ApiModelProperty(value = "订单状态", required = true, example = "1", notes = "参见 PayTransactionStatusEnum 枚举")
    private Integer status;

    @ApiModelProperty(value = "交易过期时间", required = true)
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
