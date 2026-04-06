package cn.iocoder.yudao.module.mes.controller.admin.wm.sn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Schema(description = "管理后台 - MES SN 码生成 Request VO")
@Data
public class MesWmSnGenerateReqVO {

    @Schema(description = "物料编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "物料编号不能为空")
    private Long itemId;

    @Schema(description = "批次号", example = "BATCH001")
    @Size(max = 100, message = "批次号长度不能超过 100 个字符")
    private String batchCode;

    @Schema(description = "生产工单编号", example = "1")
    private Long workOrderId;

    @Schema(description = "生成数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "100")
    @NotNull(message = "生成数量不能为空")
    @Min(value = 1, message = "生成数量最小为 1")
    @Max(value = 1000, message = "生成数量最大为 1000")
    private Integer count;

}
