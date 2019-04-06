package cn.iocoder.mall.user.api.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户访问日志添加 DTO
 */
public class UserAccessLogAddDTO {

    /**
     * 用户编号 - 空
     */
    public static final Integer USER_ID_NULL = 0;

    /**
     * 用户编号.
     *
     * 当用户为空时，该值为0
     */
    @NotNull(message = "用户编号不能为空")
    private Integer userId;
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

    public Integer getUserId() {
        return userId;
    }

    public UserAccessLogAddDTO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public UserAccessLogAddDTO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public UserAccessLogAddDTO setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public UserAccessLogAddDTO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public UserAccessLogAddDTO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public UserAccessLogAddDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public UserAccessLogAddDTO setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public UserAccessLogAddDTO setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

}