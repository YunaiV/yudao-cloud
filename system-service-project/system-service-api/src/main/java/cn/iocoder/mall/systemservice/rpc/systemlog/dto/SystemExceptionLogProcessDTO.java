package cn.iocoder.mall.systemservice.rpc.systemlog.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 系统异常日志处理 DTO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemExceptionLogProcessDTO extends PageParam {

    /**
     * 系统异常日志编号
     */
    private Integer logId;
    /**
     * 处理状态
     */
    private Integer processStatus;
    /**
     * 处理管理员编号
     */
    private Integer processAdminId;

}
