package cn.iocoder.yudao.module.pay.api.refund.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "RPC 服务 - 退款单信息 Response DTO")
@Data
public class PayRefundRespDTO {

    @Schema(description = "退款单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    // ========== 退款相关字段 ==========

    @Schema(description = "退款状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "10") // PayRefundStatusEnum 枚举
    private Integer status;

    @Schema(description = "退款金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "101")
    private Integer refundPrice;

    // ========== 商户相关字段 ==========

    @Schema(description = "商户订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M101") // 例如说，内部系统 A 的订单号。需要保证每个 PayMerchantDO 唯一
    private String merchantOrderId;

    @Schema(description = "退款成功时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime successTime;

}
