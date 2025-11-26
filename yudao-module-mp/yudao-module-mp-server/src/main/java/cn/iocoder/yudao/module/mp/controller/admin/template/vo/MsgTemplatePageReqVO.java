package cn.iocoder.yudao.module.mp.controller.admin.template.vo;

import java.time.LocalDateTime;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * @author dengsl
 */
@Schema(description = "管理后台 - 消息模板分页 Request VO")
@Data
public class MsgTemplatePageReqVO extends PageParam {

    @Schema(description = "appId", example = "9758")
    private String appId;

    @Schema(description = "公众号账号的编号", example = "9758")
    private Long accountId;

    @Schema(description = "公众号模板ID", example = "14517")
    private String templateId;

    @Schema(description = "模版名称", example = "赵六")
    private String name;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "模板内容")
    private String content;

    @Schema(description = "消息内容")
    private String data;

    @Schema(description = "链接", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description = "小程序appid")
    private String miniProgramAppId;

    @Schema(description = "小程序页面路径")
    private String miniProgramPagePath;

    @Schema(description = "是否有效", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime[] createTime;

}