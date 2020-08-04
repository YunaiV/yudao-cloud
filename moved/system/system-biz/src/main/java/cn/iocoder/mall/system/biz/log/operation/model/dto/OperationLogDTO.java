package cn.iocoder.mall.system.biz.log.operation.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 操作日志
 *
 * @author hccake
 * @date 2020-05-15 15:12:53
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "操作日志")
public class OperationLogDTO{
    private static final long serialVersionUID = 1L;

    /**
     * 链路追踪编号
     */
    @ApiModelProperty(value = "链路追踪编号")
    private String traceId;
    /**
     * 账号编号
     */
    @ApiModelProperty(value = "账号编号")
    private Integer accountId;
    /**
     * 应用名
     */
    @ApiModelProperty(value = "应用名")
    private String applicationName;
    /**
     * 访问地址
     */
    @ApiModelProperty(value = "访问地址")
    private String uri;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String params;
    /**
     * http 方法
     */
    @ApiModelProperty(value = "http 方法")
    private String method;
    /**
     * userAgent
     */
    @ApiModelProperty(value = "userAgent")
    private String userAgent;
    /**
     * ip
     */
    @ApiModelProperty(value = "ip")
    private String ip;
    /**
     * 请求时间
     */
    @ApiModelProperty(value = "请求时间")
    private LocalDateTime startTime;
    /**
     * 响应时长 -- 毫秒级
     */
    @ApiModelProperty(value = "响应时长 -- 毫秒级")
    private Integer responseTime;
    /**
     * 日志消息
     */
    @ApiModelProperty(value = "日志消息")
    private String msg;
    /**
     * 操作状态
     */
    @ApiModelProperty(value = "操作状态")
    private Integer status;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String operator;
}
