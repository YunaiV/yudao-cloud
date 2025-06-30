package cn.iocoder.yudao.framework.common.biz.system.dict.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "RPC 服务 - 字典数据 Response DTO")
@Data
public class DictDataRespDTO {

    @Schema(description = "字典标签", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋道")
    private String label;

    @Schema(description = "字典值", requiredMode = Schema.RequiredMode.REQUIRED, example = "iocoder")
    private String value;

    @Schema(description = "字典类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "sys_common_sex")
    private String dictType;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status; // 参见 CommonStatusEnum 枚举

}
