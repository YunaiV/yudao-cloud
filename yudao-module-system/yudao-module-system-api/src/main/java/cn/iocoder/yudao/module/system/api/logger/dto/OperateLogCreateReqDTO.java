package cn.iocoder.yudao.module.system.api.logger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Schema(name = "RPC 服务 - 系统操作日志 Create Request DTO")
@Data
public class OperateLogCreateReqDTO {

    @Schema(description = "链路追踪编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;

    @Schema(description = "用户编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "666")
    @NotNull(message = "用户编号不能为空")
    private Long userId;
    @Schema(description = "用户类型，参见 UserTypeEnum 枚举", requiredMode = Schema.RequiredMode.REQUIRED, example = "2" )
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    @Schema(description = "操作模块类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "订单")
    @NotEmpty(message = "操作模块类型不能为空")
    private String type;
    @Schema(description = "操作名", requiredMode = Schema.RequiredMode.REQUIRED, example = "创建订单")
    @NotEmpty(message = "操作名不能为空")
    private String subType;
    @Schema(description = "操作模块业务编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "188")
    @NotNull(message = "操作模块业务编号不能为空")
    private Long bizId;
    @Schema(description = "操作内容", requiredMode = Schema.RequiredMode.REQUIRED,
            example = "修改编号为 1 的用户信息，将性别从男改成女，将姓名从芋道改成源码")
    @NotEmpty(message = "操作内容不能为空")
    private String action;
    @Schema(description = "拓展字段", example = "{\"orderId\": \"1\"}")
    private String extra;

    @Schema(description = "请求方法名", requiredMode = Schema.RequiredMode.REQUIRED, example = "GET")
    @NotEmpty(message = "请求方法名不能为空")
    private String requestMethod;
    @Schema(description = "请求地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "/order/get")
    @NotEmpty(message = "请求地址不能为空")
    private String requestUrl;
    @Schema(description = "用户 IP", requiredMode = Schema.RequiredMode.REQUIRED, example = "127.0.0.1")
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;
    @Schema(description = "浏览器 UserAgent", requiredMode = Schema.RequiredMode.REQUIRED, example = "Mozilla/5.0")
    @NotEmpty(message = "浏览器 UA 不能为空")
    private String userAgent;

}
