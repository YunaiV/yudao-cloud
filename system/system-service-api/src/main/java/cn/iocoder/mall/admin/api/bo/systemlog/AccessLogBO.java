package cn.iocoder.mall.admin.api.bo.systemlog;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 17:26
 */
@Data
@Accessors(chain = true)
public class AccessLogBO implements Serializable {

    private String traceId;

    private Integer userId;

    private Integer userType;

    private String applicationName;

    private String uri;

    private String queryString;

    private String method;

    private String userAgent;

    private String ip;

    private Date startTime;

    private Integer responseTime;

    private Integer errorCode;
}
