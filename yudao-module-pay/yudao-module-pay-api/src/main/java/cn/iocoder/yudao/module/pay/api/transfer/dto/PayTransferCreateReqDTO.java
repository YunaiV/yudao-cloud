package cn.iocoder.yudao.module.pay.api.transfer.dto;

import cn.iocoder.yudao.framework.common.validation.InEnum;
import cn.iocoder.yudao.module.pay.enums.transfer.PayTransferTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Schema(description = "RPC 服务 - 转账单创建 Request DTO")
@Data
public class PayTransferCreateReqDTO {

    @Schema(description = "应用编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotNull(message = "应用编号不能为空")
    private Long appId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "转账类型不能为空")
    @InEnum(PayTransferTypeEnum.class)
    private Integer type;

    @Schema(description = "商户订单编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "M101") // 例如说，内部系统 A 的订单号。需要保证每个 PayMerchantDO 唯一
    @NotEmpty(message = "商户订单编号不能为空")
    private String merchantOrderId;

    @Schema(description = "转账金额，单位：分", requiredMode = Schema.RequiredMode.REQUIRED, example = "80")
    @Min(value = 1, message = "转账金额必须大于零")
    @NotNull(message = "转账金额不能为空")
    private Integer price;

    @Schema(description = "转账标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "我是标题")
    @NotEmpty(message = "转账标题不能为空")
    private String title;

    @Schema(description = "收款方信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "收款方信息不能为空")
    private Map<String, String> payeeInfo;

}
