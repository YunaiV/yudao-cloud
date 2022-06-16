package cn.iocoder.mall.managementweb.controller.systemlog.vo;

import lombok.*;
import io.swagger.annotations.*;
import java.util.*;

@ApiModel("系统访问日志 VO")
@Data
public class SystemAccessLogVO {

    @ApiModelProperty(value = "编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户编号", example = "1024")
    private Integer userId;
    @ApiModelProperty(value = "用户类型", example = "1")
    private Integer userType;
    @ApiModelProperty(value = "链路追踪编号", example = "89aca178-a370-411c-ae02-3f0d672be4ab")
    private String traceId;
    @ApiModelProperty(value = "应用名", required = true, example = "user-shop-application")
    private String applicationName;
    @ApiModelProperty(value = "访问地址", required = true, example = "/management-api/system-access-log/page")
    private String uri;
    @ApiModelProperty(value = "参数", required = true, example = "pageNo=1&pageSize=10")
    private String queryString;
    @ApiModelProperty(value = "http 方法", required = true, example = "GET")
    private String method;
    @ApiModelProperty(value = "userAgent", required = true, example = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0")
    private String userAgent;
    @ApiModelProperty(value = "ip", required = true, example = "127.0.0.1")
    private String ip;
    @ApiModelProperty(value = "请求时间", required = true)
    private Date startTime;
    @ApiModelProperty(value = "响应时长 -- 毫秒级", required = true, example = "1024")
    private Integer responseTime;
    @ApiModelProperty(value = "错误码", required = true, example = "0")
    private Integer errorCode;
    @ApiModelProperty(value = "错误提示", example = "你的昵称过长~")
    private String errorMessage;

}
