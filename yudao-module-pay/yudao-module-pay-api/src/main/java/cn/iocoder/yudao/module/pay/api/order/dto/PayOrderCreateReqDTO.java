package cn.iocoder.yudao.module.pay.api.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Schema(description = "RPC 服务 - 支付单创建 Request DTO")
@Data
public class PayOrderCreateReqDTO implements Serializable {

    public static final int SUBJECT_MAX_LENGTH = 32;

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

    @Schema(description = "商品标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "我是标题")
    @NotEmpty(message = "商品标题不能为空")
    @Length(max = 32, message = "商品标题不能超过 32")
    private String subject;

    @Schema(description = "商品描述", example = "我是描述")
    @Length(max = 128, message = "商品描述信息长度不能超过128")
    private String body;

    // ========== 订单相关字段 ==========

    @Schema(description = "支付金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @NotNull(message = "支付金额不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "支付金额必须大于零")
    private Integer price;

    @Schema(description = "支付过期时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "支付过期时间不能为空")
    private LocalDateTime expireTime;

}
