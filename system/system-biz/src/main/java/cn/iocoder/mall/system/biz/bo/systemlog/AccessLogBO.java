package cn.iocoder.mall.system.biz.bo.systemlog;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/** // TODO FROM 芋艿 to 2447007062：最好加下字段的注释哈；
 * @author:mac
 * @descriptio
 * @create: 2020-5-12 20：43：00
 */
@Data
@Accessors(chain = true)
public class AccessLogBO implements Serializable {

    private String traceId;

    private Integer accountId;

    private String applicationName;

    private String uri;

    private String queryString;

    private String method;

    private String userAgent;

    private String ip;

    private Date startTime;

    private Integer responseTime;

    private Integer errorCode;

    private String errorMessage;
}
