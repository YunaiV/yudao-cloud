package cn.iocoder.mall.shopweb.controller.pay.vo.transaction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("支付交易提交 Response VO")
@Data
@Accessors(chain = true)
public class PayTransactionSubmitRespVO {

    @ApiModelProperty(value = "支付交易拓展单编号", required = true, example = "1")
    private Integer id;

    @ApiModelProperty(value = "调用三方平台的响应结果", required = true)
    private String invokeResponse;

}
