package cn.iocoder.mall.systemservice.rpc.systemlog.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 系统访问日志 VO
*/
@Data
@Accessors(chain = true)
public class SystemAccessLogVO implements Serializable {

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
     */
    private Integer userType;
    /**
     * 链路追踪编号
     */
    private String traceId;
    /**
     * 应用名
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
     */
    private Integer errorCode;
    /**
     * 错误提示
     */
    private String errorMessage;

}
