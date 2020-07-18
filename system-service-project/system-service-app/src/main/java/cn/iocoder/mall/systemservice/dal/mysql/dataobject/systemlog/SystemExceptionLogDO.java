package cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.admin.AdminDO;
import cn.iocoder.mall.systemservice.enums.systemlog.SystemExceptionLogProcessStatusEnum;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统异常日志 DO
 */
@TableName("system_exception_log")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemExceptionLogDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户类型
     *
     * 枚举 {@link UserTypeEnum}
     */
    private Integer userType;
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

    /**
     * 处理状态
     *
     * 外键 {@link SystemExceptionLogProcessStatusEnum}
     */
    private Integer processStatus;
    /**
     * 处理时间
     */
    private Date processTime;
    /**
     * 处理管理员编号
     *
     * 外键 {@link AdminDO#getId()}
     */
    private Integer processAdminId;

}
