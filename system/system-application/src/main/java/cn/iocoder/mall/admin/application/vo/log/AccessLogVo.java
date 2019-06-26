package cn.iocoder.mall.admin.application.vo.log;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author:yuxj
 * @descriptio
 * @create:2019-06-23 17:04
 */

@ApiModel("访问日志 VO")
@Data
@Accessors(chain = true)
public class AccessLogVo {


    @ApiModelProperty(value = "链路追踪编号", required = true, example = "1")
    private String traceId;

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer userId;

    @ApiModelProperty(value = "用户类型", required = true, example = "1")
    private Integer userType;

    @ApiModelProperty(value = "应用名", required = true, example = "1")
    private String applicationName;

    @ApiModelProperty(value = "访问地址", required = true, example = "1")
    private String uri;

    @ApiModelProperty(value = "请求参数", required = true, example = "1")
    private String queryString;

    @ApiModelProperty(value = "http 请求方法", required = true, example = "1")
    private String method;

    @ApiModelProperty(value = "User-Agent ", required = true, example = "1")
    private String userAgent;

    @ApiModelProperty(value = "ip", required = true, example = "1")
    private String ip;

    @ApiModelProperty(value = "请求时间", required = true, example = "1")
    private Date startTime;

    @ApiModelProperty(value = "响应时长", required = true, example = "1")
    private Integer responseTime;

    @ApiModelProperty(value = "错误码", required = true, example = "1")
    private Integer errorCode;

}
