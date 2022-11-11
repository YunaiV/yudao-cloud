package cn.iocoder.yudao.module.infra.api.logger.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@ApiModel("RPC 服务 - API 错误日志创建 Request DTO")
@Data
public class ApiErrorLogCreateReqDTO {

    @ApiModelProperty(value = "链路追踪编号", example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;

    @ApiModelProperty(value = "用户编号", required = true, example = "1024")
    private Long userId;
    @ApiModelProperty(value = "用户类型", required = true, example = "1")
    private Integer userType;
    @ApiModelProperty(value = "应用名", required = true, example = "system-server")
    @NotNull(message = "应用名不能为空")
    private String applicationName;

    @ApiModelProperty(value = "请求方法名", required = true, example = "GET")
    @NotNull(message = "http 请求方法不能为空")
    private String requestMethod;
    @ApiModelProperty(value = "请求地址", required = true, example = "/xxx/yyy")
    @NotNull(message = "访问地址不能为空")
    private String requestUrl;
    @ApiModelProperty(value = "请求参数", required = true)
    @NotNull(message = "请求参数不能为空")
    private String requestParams;
    @ApiModelProperty(value = "用户 IP", required = true, example = "127.0.0.1")
    @NotNull(message = "ip 不能为空")
    private String userIp;
    @ApiModelProperty(value = "浏览器 UserAgent", required = true, example = "Mozilla/5.0")
    @NotNull(message = "User-Agent 不能为空")
    private String userAgent;

    @ApiModelProperty(value = "异常时间", required = true)
    @NotNull(message = "异常时间不能为空")
    private LocalDateTime exceptionTime;
    @ApiModelProperty(value = "异常名", required = true)
    @NotNull(message = "异常名不能为空")
    private String exceptionName;
    @ApiModelProperty(value = "异常发生的类全名", required = true)
    @NotNull(message = "异常发生的类全名不能为空")
    private String exceptionClassName;
    @ApiModelProperty(value = "异常发生的类文件", required = true)
    @NotNull(message = "异常发生的类文件不能为空")
    private String exceptionFileName;
    @ApiModelProperty(value = "异常发生的方法名", required = true)
    @NotNull(message = "异常发生的方法名不能为空")
    private String exceptionMethodName;
    @ApiModelProperty(value = "异常发生的方法所在行", required = true)
    @NotNull(message = "异常发生的方法所在行不能为空")
    private Integer exceptionLineNumber;
    @ApiModelProperty(value = "异常的栈轨迹异常的栈轨迹", required = true)
    @NotNull(message = "异常的栈轨迹不能为空")
    private String exceptionStackTrace;
    @ApiModelProperty(value = "异常导致的根消息", required = true)
    @NotNull(message = "异常导致的根消息不能为空")
    private String exceptionRootCauseMessage;
    @ApiModelProperty(value = "异常导致的消息", required = true)
    @NotNull(message = "异常导致的消息不能为空")
    private String exceptionMessage;

}
