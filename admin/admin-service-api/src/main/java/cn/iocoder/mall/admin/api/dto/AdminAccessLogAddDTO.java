package cn.iocoder.mall.admin.api.dto;


import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 管理员访问日志添加 DTO
 */
public class AdminAccessLogAddDTO {

    /**
     * 管理员编号 - 空
     */
    public static final Integer ADMIN_ID_NULL = 0;

    /**
     * 管理员编号.
     *
     * 当管理员为空时，该值为0
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer adminId;
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

    public Integer getAdminId() {
        return adminId;
    }

    public AdminAccessLogAddDTO setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public AdminAccessLogAddDTO setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getQueryString() {
        return queryString;
    }

    public AdminAccessLogAddDTO setQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public AdminAccessLogAddDTO setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public AdminAccessLogAddDTO setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public AdminAccessLogAddDTO setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public Date getStartTime() {
        return startTime;
    }

    public AdminAccessLogAddDTO setStartTime(Date startTime) {
        this.startTime = startTime;
        return this;
    }

    public Integer getResponseTime() {
        return responseTime;
    }

    public AdminAccessLogAddDTO setResponseTime(Integer responseTime) {
        this.responseTime = responseTime;
        return this;
    }

}