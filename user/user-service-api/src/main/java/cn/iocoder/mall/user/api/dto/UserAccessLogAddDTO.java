package cn.iocoder.mall.user.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户访问日志添加 DTO
 */
@Data
@Accessors(chain = true)
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

}
