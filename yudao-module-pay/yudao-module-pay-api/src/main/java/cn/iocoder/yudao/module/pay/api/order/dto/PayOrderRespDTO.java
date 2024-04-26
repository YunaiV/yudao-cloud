package cn.iocoder.yudao.module.pay.api.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 支付单信息 Response DTO")
@Data
public class PayOrderRespDTO {

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    private Long id;

    @Schema(description = "渠道编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "wx_pub") // PayChannelEnum 枚举
    private String channelCode;

    // ========== 商户相关字段 ==========

    @Schema(description = "商户订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M101") // 例如说，内部系统 A 的订单号。需要保证每个 PayMerchantDO 唯一
    private String merchantOrderId;

    // ========== 订单相关字段 ==========

    @Schema(description = "支付金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "101")
    private Integer price;

    @Schema(description = "支付状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "10") // PayOrderStatusEnum 枚举
    private Integer status;

    // ========== 渠道相关字段 ==========

}
