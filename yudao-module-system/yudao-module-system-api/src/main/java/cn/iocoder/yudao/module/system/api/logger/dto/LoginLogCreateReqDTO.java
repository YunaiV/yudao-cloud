package cn.iocoder.yudao.module.system.api.logger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 登录日志创建 Request DTO
 *
 * @author 芋道源码
 */
@Data
public class LoginLogCreateReqDTO {

    @ApiModelProperty(value = "日志类型", required = true, example = "1", notes = "参见 LoginLogTypeEnum 枚举类")
    @NotNull(message = "日志类型不能为空")
    private Integer logType;

    @ApiModelProperty(value = "链路追踪编号", required = true, example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;

    @ApiModelProperty(value = "用户编号", example = "666")
    private Long userId;
    @ApiModelProperty(value = "用户类型", required = true, example = "2", notes = "参见 UserTypeEnum 枚举")
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    @ApiModelProperty(value = "用户账号", required = true, example = "yudao")
    @NotBlank(message = "用户账号不能为空")
    @Size(max = 30, message = "用户账号长度不能超过30个字符")
    private String username;

    @ApiModelProperty(value = "登录结果", required = true, example = "1", notes = "参见 LoginResultEnum 枚举类")
    @NotNull(message = "登录结果不能为空")
    private Integer result;

    @ApiModelProperty(value = "用户 IP", required = true, example = "127.0.0.1")
    @NotEmpty(message = "用户 IP 不能为空")
    private String userIp;

    @ApiModelProperty(value = "浏览器 UserAgent", required = true, example = "Mozilla/5.0")
    private String userAgent;

}
