package cn.iocoder.mall.admin.api.dto;


import cn.iocoder.common.framework.vo.CommonResult;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 访问日志添加 DTO
 */
@Data
@Accessors(chain = true)
public class AccessLogAddDTO implements Serializable {

    /**
     * 用户编号 - 空
     */
    public static final Integer USER_ID_NULL = 0;
    /**
     * 链路追踪编号
     *
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     */
    @NotNull(message = "链路追踪编号不能为空")
    private String traceId;
    /**
     * 用户编号.
     *
     * 当管理员为空时，该值为 {@link #USER_ID_NULL}
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
    /**
     * 用户类型
     */
    @NotNull(message = "用户类型不能为空")
    private Integer userType;
    /**
     * 应用名
     *
     * 目前读取 spring.application.name
     */
    @NotNull(message = "应用名不能为空")
    private String applicationName;
    /**
     * 访问地址
     */
    @NotNull(message = "访问地址不能为空")
    private String uri;
    /**
     * 参数
     */
    @NotNull(message = "请求参数不能为空")
    private String queryString;
    /**
     * http 方法
     */
    @NotNull(message = "http 请求方法不能为空")
    private String method;
    /**
     * User Agent
     */
    @NotNull(message = "User-Agent 不能为空")
    private String userAgent;
    /**
     * ip
     */
    @NotNull(message = "ip 不能为空")
    private String ip;
    /**
     * 请求时间
     */
    @NotNull(message = "请求时间不能为空")
    private Date startTime;
    /**
     * 响应时长 -- 毫秒级
     */
    @NotNull(message = "响应时长不能为空")
    private Integer responseTime;
    /**
     * 错误码
     *
     * 目前的结果，是使用 {@link CommonResult#getCode()} 属性
     */
    @NotNull(message = "错误码不能为空")
    private Integer errorCode;
    /**
     * 错误提示
     *
     * 目前的结果，是使用 {@link CommonResult#getMessage()} 属性
     */
    private String errorMessage;

}
