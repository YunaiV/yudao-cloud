package cn.iocoder.mall.systemservice.rpc.systemlog.dto;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.common.framework.vo.PageParam;
import cn.iocoder.mall.systemservice.enums.systemlog.SystemExceptionLogProcessStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "系统异常日志编号不能为空")
    private Integer logId;
    /**
     * 处理状态
     */
    @NotNull(message = "处理状态不能为空")
    @InEnum(value = SystemExceptionLogProcessStatusEnum.class, message = "处理状态必须是 {value}")
    private Integer processStatus;
    /**
     * 处理管理员编号
     */
    @NotNull(message = "处理管理员编号不能为空")
    private Integer processAdminId;

}
