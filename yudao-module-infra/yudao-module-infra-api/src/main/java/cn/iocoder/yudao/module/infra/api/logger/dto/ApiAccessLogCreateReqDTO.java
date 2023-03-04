package cn.iocoder.yudao.module.infra.api.logger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Schema(description = "RPC 服务 - API 访问日志创建 Request DTO")
@Data
public class ApiAccessLogCreateReqDTO {

    @Schema(description = "链路追踪编号", example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;

    @Schema(description = "用户编号", required = true, example = "1024")
    private Long userId;
    @Schema(description = "用户类型", required = true, example = "1")
    private Integer userType;
    @Schema(description = "应用名", required = true, example = "system-server")
    @NotNull(message = "应用名不能为空")
    private String applicationName;

    @Schema(description = "请求方法名", required = true, example = "GET")
    @NotNull(message = "http 请求方法不能为空")
    private String requestMethod;
    @Schema(description = "请求地址", required = true, example = "/xxx/yyy")
    @NotNull(message = "访问地址不能为空")
    private String requestUrl;
    @Schema(description = "请求参数", required = true)
    @NotNull(message = "请求参数不能为空")
    private String requestParams;
    @Schema(description = "用户 IP", required = true, example = "127.0.0.1")
    @NotNull(message = "ip 不能为空")
    private String userIp;
    @Schema(description = "浏览器 UserAgent", required = true, example = "Mozilla/5.0")
    @NotNull(message = "User-Agent 不能为空")
    private String userAgent;

    @Schema(description = "开始时间", required = true)
    @NotNull(message = "开始请求时间不能为空")
    private LocalDateTime beginTime;
    @Schema(description = "结束时间", required = true)
    @NotNull(message = "结束请求时间不能为空")
    private LocalDateTime endTime;
    @Schema(description = "执行时长，单位：毫秒", required = true)
    @NotNull(message = "执行时长不能为空")
    private Integer duration;
    @Schema(description = "结果码", required = true)
    @NotNull(message = "错误码不能为空")
    private Integer resultCode;
    @Schema(description = "结果提示")
    private String resultMsg;

}