package cn.iocoder.mall.pay.api.dto.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel("支付交易创建 DTO")
@Data
@Accessors(chain = true)
public class PayTransactionCreateDTO implements Serializable {

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    @ApiModelProperty(value = "发起交易的 IP", required = true, example = "192.168.10.1")
    @NotEmpty(message = "IP 不能为空")
    private String createIp;

    @ApiModelProperty(value = "订单号不能为空", required = true, example = "1024")
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

    @ApiModelProperty(value = "商品名", required = true, example = "芋道源码")
    @NotEmpty(message = "商品名不能为空")
    @Length(max = 32, message = "商品名不能超过32")
    private String orderSubject;

    @ApiModelProperty(value = "订单商品描述", required = true, example = "绵啾啾的")
    @NotEmpty(message = "商品描述不能为空")
    @Length(max = 128, message = "商品描述长度不能超过128")
    private String orderDescription;

    @ApiModelProperty(value = "订单商品备注", example = "绵啾啾的")
    @Length(max = 256, message = "商品备注长度不能超过256")
    private String orderMemo;

    @ApiModelProperty(value = "支付金额，单位：分。", required = true, example = "10")
    @NotNull(message = "金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "金额必须大于零")
    private Integer price;

    @ApiModelProperty(value = "交易过期时间", required = true)
    @NotNull(message = "交易过期时间不能为空")
    private Date expireTime;

}
