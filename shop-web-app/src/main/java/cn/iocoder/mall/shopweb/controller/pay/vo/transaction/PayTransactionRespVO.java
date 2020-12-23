package cn.iocoder.mall.shopweb.controller.pay.vo.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("支付交易 Response VO")
@Data
@Accessors(chain = true)
public class PayTransactionRespVO {

    @ApiModelProperty(value = "交易编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    private String appId;

    @ApiModelProperty(value = "订单号不能为空", required = true, example = "1024")
    private String orderId;

    @ApiModelProperty(value = "商品名", required = true, example = "芋道源码")
    private String orderSubject;

    @ApiModelProperty(value = "订单商品描述", required = true, example = "绵啾啾的")
    private String orderDescription;

    @ApiModelProperty(value = "支付金额，单位：分。", required = true, example = "10")
    private Integer price;

    @ApiModelProperty(value = "订单状态", required = true, example = "1", notes = "参见 PayTransactionStatusEnum 枚举")
    private Integer status;

    @ApiModelProperty(value = "交易过期时间", required = true)
    private Date expireTime;

}
