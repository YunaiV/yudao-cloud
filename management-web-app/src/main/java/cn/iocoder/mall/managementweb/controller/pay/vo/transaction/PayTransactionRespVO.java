package cn.iocoder.mall.managementweb.controller.pay.vo.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("支付交易单 Response VO")
@Data
@Accessors(chain = true)
public class PayTransactionRespVO {

    @ApiModelProperty(value = "交易编号", required = true, example = "POd4RC6a")
    private Integer id;

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Integer userId;

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

    @ApiModelProperty(value = "回调业务线完成时间")
    private Date finishTime;

    @ApiModelProperty(value = "成功支付的交易拓展编号", example = "1024")
    private Integer extensionId;

    @ApiModelProperty(value = "支付成功的支付渠道", example = "1", notes = "参见 PayChannelEnum 枚举")
    private Integer payChannel;

    @ApiModelProperty(value = "第三方支付成功的时间")
    private Date paymentTime;

    @ApiModelProperty(value = "收到第三方系统通知的时间")
    private Date notifyTime;

    @ApiModelProperty(value = "第三方的流水号", example = "11122233344444")
    private String tradeNo;

    @ApiModelProperty(value = "添加时间", required = true)
    private Date createTime;

    // ========== 退款相关 ==========

    @ApiModelProperty(value = "退款总金额，单位：分", example = "100")
    private Integer refundTotal;

}
