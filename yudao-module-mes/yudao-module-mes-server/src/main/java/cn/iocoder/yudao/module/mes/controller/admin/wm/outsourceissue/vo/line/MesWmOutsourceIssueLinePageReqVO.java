package cn.iocoder.yudao.module.mes.controller.admin.wm.outsourceissue.vo.line;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - MES 外协发料单行分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MesWmOutsourceIssueLinePageReqVO extends PageParam {

    @Schema(description = "发料单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "发料单ID不能为空")
    private Long issueId;

}
