package cn.iocoder.yudao.module.member.api.config.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 用户信息 Response DTO")
@Data
public class MemberConfigRespDTO {

    @Schema(description = "积分抵扣开关", requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
    private Boolean pointTradeDeductEnable;

    @Schema(description = "积分抵扣，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Integer pointTradeDeductUnitPrice; // 1 积分抵扣多少分

    @Schema(description = "积分抵扣最大值", requiredMode = Schema.RequiredMode.REQUIRED, example = "200")
    private Integer pointTradeDeductMaxPrice;

    @Schema(description = "1 元赠送多少分", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    private Integer pointTradeGivePoint;

}
