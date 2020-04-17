package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.system.api.dto.systemlog.AccessLogAddDTO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 访问日志 DO
 */
@Data
@Accessors(chain = true)
@TableName("access_log")
public class AccessLogDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 链路追踪编号
     *
     * 一般来说，通过链路追踪编号，可以将访问日志，错误日志，链路追踪日志，logger 打印日志等，结合在一起，从而进行排错。
     */
    private String traceId;
    /**
     * 用户编号.
     *
     * 当管理员为空时，该值为 {@link AccessLogAddDTO#USER_ID_NULL}
     */
    private Integer userId;
    /**
     * 用户类型
     */
    private Integer userType;
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
     * 请求时间
     */
    private Date startTime;
    /**
     * 响应时长 -- 毫秒级
     */
    private Integer responseTime;
    /**
     * 错误码
     *
     * 目前的结果，是使用 {@link CommonResult#getCode()} 属性
     */
    private Integer errorCode;
    /**
     * 错误提示
     *
     * 目前的结果，是使用 {@link CommonResult#getMessage()} 属性
     */
    private String errorMessage;

}
