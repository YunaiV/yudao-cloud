package cn.iocoder.mall.admin.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 管理员访问日志添加 DTO
 */
@Data
@Accessors(chain = true)
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

}
