package cn.iocoder.mall.managementweb.controller.systemlog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@ApiModel("系统异常日志明细 DTO")
@Data
public class SystemExceptionLogDetailVO {

    @ApiModel("管理员")
    @Data
    @Accessors(chain = true)
    public static class Admin {

        @ApiModelProperty(value = "管理员编号", required = true, example = "1")
        private Integer id;
        @ApiModelProperty(value = "真实名字", required = true, example = "小王")
        private String name;

    }

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户编号", example = "1024")
    private Integer userId;
    @ApiModelProperty(value = "用户类型", example = "1")
    private Integer userType;
    @ApiModelProperty(value = "链路追踪编号", example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;
    @ApiModelProperty(value = "应用名", required = true, example = "user-shop-application")
    private String applicationName;
    @ApiModelProperty(value = "访问地址", required = true, example = "/management-api/system-access-log/page")
    private String uri;
    @ApiModelProperty(value = "参数", required = true, example = "pageNo=1&pageSize=10")
    private String queryString;
    @ApiModelProperty(value = "http 方法", required = true, example = "GET")
    private String method;
    @ApiModelProperty(value = "userAgent", required = true, example = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
    private String userAgent;
    @ApiModelProperty(value = "ip", required = true, example = "127.0.0.1")
    private String ip;
    @ApiModelProperty(value = "异常发生时间", required = true)
    private Date exceptionTime;
    @ApiModelProperty(value = "异常名, {@link Throwable#getClass()} 的类全名", required = true)
    private String exceptionName;
    @ApiModelProperty(value = "异常导致的消息, {@link cn.iocoder.common.framework.util.ExceptionUtil#getMessage(Throwable)}", required = true)
    private String exceptionMessage;
    @ApiModelProperty(value = "异常导致的根消息, {@link cn.iocoder.common.framework.util.ExceptionUtil#getRootCauseMessage(Throwable)}", required = true)
    private String exceptionRootCauseMessage;
    @ApiModelProperty(value = "异常的栈轨迹, {@link cn.iocoder.common.framework.util.ExceptionUtil#getServiceException(Exception)}", required = true)
    private String exceptionStackTrace;
    @ApiModelProperty(value = "异常发生的类全名, {@link StackTraceElement#getClassName()}", required = true)
    private String exceptionClassName;
    @ApiModelProperty(value = "异常发生的类文件, {@link StackTraceElement#getFileName()}", required = true)
    private String exceptionFileName;
    @ApiModelProperty(value = "异常发生的方法名, {@link StackTraceElement#getMethodName()}", required = true)
    private String exceptionMethodName;
    @ApiModelProperty(value = "异常发生的方法所在行, {@link StackTraceElement#getLineNumber()}", required = true)
    private Integer exceptionLineNumber;
    @ApiModelProperty(value = "处理状态", required = true, notes = "对应 SystemExceptionLogProcessStatusEnum 枚举类", example = "1")
    private Integer processStatus;
    @ApiModelProperty(value = "处理时间")
    private Date processTime;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    /**
     * 处理的管理员信息
     */
    private Admin processAdmin;

}
