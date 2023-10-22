package cn.iocoder.yudao.module.system.api.errorcode.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "RPC 服务 - 错误码 Response DTO")
@Data
public class ErrorCodeRespDTO {

    /**
     * 错误码编码
     */
    @Schema(description = "错误码编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    private Integer code;
    /**
     * 错误码错误提示
     */
    @Schema(description = "错误码错误提示", requiredMode = Schema.RequiredMode.REQUIRED, example = "业务不能为空")
    private String message;
    /**
     * 更新时间
     */
    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;

}
