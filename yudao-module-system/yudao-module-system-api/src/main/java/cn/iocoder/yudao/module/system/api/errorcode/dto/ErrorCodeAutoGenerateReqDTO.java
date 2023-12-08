package cn.iocoder.yudao.module.system.api.errorcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(description = "RPC 服务 - 错误码自动生成 Request DTO")
@Data
public class ErrorCodeAutoGenerateReqDTO {

    @Schema(description = "应用名", requiredMode = Schema.RequiredMode.REQUIRED, example = "yudao")
    @NotNull(message = "应用名不能为空")
    private String applicationName;

    @Schema(description = "错误码编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @NotNull(message = "错误码编码不能为空")
    private Integer code;

    @Schema(description = "错误码错误提示", requiredMode = Schema.RequiredMode.REQUIRED, example = "业务不能为空")
    @NotEmpty(message = "错误码错误提示不能为空")
    private String message;

}
