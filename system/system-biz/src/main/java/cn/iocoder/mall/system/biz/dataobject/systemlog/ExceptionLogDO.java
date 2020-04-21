package cn.iocoder.mall.system.biz.dataobject.systemlog;

import cn.iocoder.common.framework.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 异常日志 DO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("exception_log")
public class ExceptionLogDO extends BaseDO {

    /**
     * 账号编号 - 空
     */
    public static final Integer ACCOUNT_ID_NULL = 0;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 账号编号
     *
     * 空值 {@link #ACCOUNT_ID_NULL}
     */
    private Integer accountId;
    /**
     * 链路追踪编号
     *
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     */
    private String traceId;
    /**
     * 应用名
     *
     * 目前读取 spring.application.name
     */
    private String applicationName;
    /**
     * 访问地址
     */
    private String uri;
    /**
     * 参数
     */
    private String queryString;
    /**
     * http 方法
     */
    private String method;
    /**
     * userAgent
     */
    private String userAgent;
    /**
     * ip
     */
    private String ip;
    /**
     * 异常发生时间
     */
    private Date exceptionTime;
    /**
     * 异常名
     *
     * {@link Throwable#getClass()} 的类全名
     */
    private String exceptionName;
    /**
     * 异常导致的消息
     *
     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getMessage(Throwable)}
     */
    private String exceptionMessage;
    /**
     * 异常导致的根消息
     *
     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getRootCauseMessage(Throwable)}
     */
    private String exceptionRootCauseMessage;
    /**
     * 异常的栈轨迹
     *
     * {@link cn.iocoder.common.framework.util.ExceptionUtil#getServiceException(Exception)}
     */
    private String exceptionStackTrace;
    /**
     * 异常发生的类全名
     *
     * {@link StackTraceElement#getClassName()}
     */
    private String exceptionClassName;
    /**
     * 异常发生的类文件
     *
     * {@link StackTraceElement#getFileName()}
     */
    private String exceptionFileName;
    /**
     * 异常发生的方法名
     *
     * {@link StackTraceElement#getMethodName()}
     */
    private String exceptionMethodName;
    /**
     * 异常发生的方法所在行
     *
     * {@link StackTraceElement#getLineNumber()}
     */
    private Integer exceptionLineNumber;

}
