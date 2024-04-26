package cn.iocoder.yudao.module.bpm.controller.admin.definition.vo.model;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Schema(description = "管理后台 - 流程模型分页 Request VO")
@Data
public class BpmModelPageReqVO extends PageParam {

    @Schema(description = "标识，精准匹配", example = "process1641042089407")
    private String key;

    @Schema(description = "名字，模糊匹配", example = "芋道")
    private String name;

    @Schema(description = "流程分类", example = "1")
    private String category;

}
