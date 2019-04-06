package cn.iocoder.mall.user.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

import java.util.Date;

/**
 * 用户访问日志 DO
 */
public class UserAccessLogDO extends DeletableDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 用户编号.
     *
     * 当用户编号为空时，该值为0
     */
    private Integer userId;
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

    public Integer getId() {
        return id;
    }

    public UserAccessLogDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public UserAccessLogDO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public UserAccessLogDO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public UserAccessLogDO setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public UserAccessLogDO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public UserAccessLogDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public UserAccessLogDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public UserAccessLogDO setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public UserAccessLogDO setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

}