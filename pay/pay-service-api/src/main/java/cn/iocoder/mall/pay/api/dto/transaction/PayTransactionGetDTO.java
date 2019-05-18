package cn.iocoder.mall.pay.api.dto.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ApiModel("支付交易获得 DTO")
@Data
@Accessors(chain = true)
public class PayTransactionGetDTO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1", hidden = true) // hidden 的原因是，Service DTO 自己传入，无需暴露的 Controller API 里
    @NotNull(message = "用户编号不能为空")
    private Integer userId;

    @ApiModelProperty(value = "应用编号", required = true, example = "POd4RC6a")
    @NotEmpty(message = "应用编号不能为空")
    private String appId;

    @ApiModelProperty(value = "订单号不能为空", required = true, example = "1024")
    @NotEmpty(message = "订单号不能为空")
    private String orderId;

}
