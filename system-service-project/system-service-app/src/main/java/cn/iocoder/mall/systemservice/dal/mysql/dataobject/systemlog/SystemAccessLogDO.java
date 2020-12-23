package cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog;

import cn.iocoder.common.framework.enums.UserTypeEnum;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 系统访问日志 DO
 *
 * 记录用户、管理员对 API 接口的调用
 *
 * TODO 优化点：考虑到架构简单，暂时记录到 MySQL。因为访问日志容易比较大，所以建议未来存储到 ES 中，同时能够提供更丰富的检索能力
 */
@TableName("system_access_log")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemAccessLogDO extends BaseDO {

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
     * 目前读取 `spring.application.name` 配置项
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
