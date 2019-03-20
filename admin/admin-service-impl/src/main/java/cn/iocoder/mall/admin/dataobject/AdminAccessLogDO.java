package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

import java.util.Date;

/**
 * 管理员访问日志 DO
 */
public class AdminAccessLogDO extends BaseDO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 管理员编号.
     *
     * 当管理员为空时，该值为0
     */
    private Integer adminId;
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

    public AdminAccessLogDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public AdminAccessLogDO setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public AdminAccessLogDO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public AdminAccessLogDO setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AdminAccessLogDO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public AdminAccessLogDO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AdminAccessLogDO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public AdminAccessLogDO setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public AdminAccessLogDO setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

}