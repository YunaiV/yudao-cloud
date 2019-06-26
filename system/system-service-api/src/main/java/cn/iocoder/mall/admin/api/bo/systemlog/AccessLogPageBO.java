package cn.iocoder.mall.admin.api.bo.systemlog;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2019-06-23 17:26
 */
@Data
@Accessors(chain = true)
public class AccessLogPageBO implements Serializable {

    /**
     * 日志数组
     */
    private List<AccessLogBO> list;
    /**
     * 总量
     */
    private Integer total;

}
