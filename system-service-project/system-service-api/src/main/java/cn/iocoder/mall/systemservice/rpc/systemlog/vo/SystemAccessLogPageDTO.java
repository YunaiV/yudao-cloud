package cn.iocoder.mall.systemservice.rpc.systemlog.vo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 系统访问日志分页 DTO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemAccessLogPageDTO extends PageParam {

    /**
    * 用户编号
    */
    private Integer userId;
    /**
    * 用户类型
    */
    private Integer userType;
    /**
    * 应用名
    */
    private String applicationName;

}
