package cn.iocoder.yudao.module.pay.api.notify.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 支付单的通知 Request DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderNotifyReqDTO {

    @Schema(description = "商户订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M101")
    @NotEmpty(message = "商户订单号不能为空")
    private String merchantOrderId;

    @Schema(description = "支付订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "P202")
    @NotNull(message = "支付订单编号不能为空")
    private Long payOrderId;

}
