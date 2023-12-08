package cn.iocoder.yudao.module.pay.api.notify.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 退款单的通知 Request DTO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayRefundNotifyReqDTO {

    @Schema(description = "商户退款单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "MR101")
    @NotEmpty(message = "商户退款单编号不能为空")
    private String merchantOrderId;

    @Schema(description = "支付订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "R303")
    @NotNull(message = "支付退款编号不能为空")
    private Long payRefundId;

}
