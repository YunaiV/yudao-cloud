package cn.iocoder.yudao.module.mes.controller.admin.wm.stocktaking.task.vo.line;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - MES 盘点任务行批量更新 Request VO")
@Data
public class MesWmStockTakingTaskLineBatchUpdateReqVO {

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "任务编号不能为空")
    private Long taskId;

    @Schema(description = "任务行列表")
    @NotEmpty(message = "任务行不能为空")
    @Valid
    private List<Item> items;

    @Data
    public static class Item {

        @Schema(description = "任务行编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
        @NotNull(message = "任务行编号不能为空")
        private Long id;

        @Schema(description = "实盘数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "98.000000")
        @NotNull(message = "实盘数量不能为空")
        private BigDecimal takingQuantity;

        @Schema(description = "备注", example = "备注")
        private String remark;

    }

}
