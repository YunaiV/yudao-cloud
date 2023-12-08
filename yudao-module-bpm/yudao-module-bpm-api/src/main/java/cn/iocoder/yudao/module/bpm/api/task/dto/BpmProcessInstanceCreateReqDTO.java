package cn.iocoder.yudao.module.bpm.api.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import java.util.Map;

@Schema(description = "RPC 服务 - 流程实例的创建 Request DTO")
@Data
public class BpmProcessInstanceCreateReqDTO {

    @Schema(description = "流程定义的标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "leave")
    @NotEmpty(message = "流程定义的标识不能为空")
    private String processDefinitionKey;

    @Schema(description = "变量实例", requiredMode = Schema.RequiredMode.REQUIRED)
    private Map<String, Object> variables;

    @Schema(description = "业务的唯一标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "业务的唯一标识不能为空")
    private String businessKey; // 例如说，请假申请的编号。通过它，可以查询到对应的实例

}
