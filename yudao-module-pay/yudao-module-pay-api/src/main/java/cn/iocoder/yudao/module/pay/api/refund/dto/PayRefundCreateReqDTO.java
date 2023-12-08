package cn.iocoder.yudao.module.pay.api.refund.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 退款单创建 Request DTO")
@Data
public class PayRefundCreateReqDTO {

    @Schema(description = "应用编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "应用编号不能为空")
    private Long appId;

    @Schema(description = "用户 IP", requiredMode = Schema.RequiredMode.REQUIRED, example = "127.0.0.1")
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    // ========== 商户相关字段 ==========

    @Schema(description = "商户订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M101") // 例如说，内部系统 A 的订单号。需要保证每个 PayMerchantDO 唯一
    @NotEmpty(message = "商户订单编号不能为空")
    private String merchantOrderId;

    @Schema(description = "商户退款编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "MR101")
    @NotEmpty(message = "商户退款编号不能为空")
    private String merchantRefundId;

    @Schema(description = "退款描述", requiredMode = Schema.RequiredMode.REQUIRED, example = "我是退款描述")
    @NotEmpty(message = "退款描述不能为空")
    @Length(max = 128, message = "退款描述长度不能超过 128")
    private String reason;

    // ========== 订单相关字段 ==========

    @Schema(description = "退款金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "60")
    @NotNull(message = "退款金额不能为空")
    @Min(value = 1, message = "退款金额必须大于零")
    private Integer price;

}
