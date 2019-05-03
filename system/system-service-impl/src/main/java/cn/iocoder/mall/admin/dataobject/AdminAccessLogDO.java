package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 管理员访问日志 DO
 */
@Data
@Accessors(chain = true)
public class AdminAccessLogDO extends DeletableDO {

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

}
