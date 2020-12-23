package cn.iocoder.mall.managementweb.controller.systemlog.dto;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("系统异常日志处理 DTO")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemExceptionLogProcessDTO extends PageParam {

    @ApiModelProperty(value = "系统异常日志编号", required = true, example = "1")
    private Integer logId;
    @ApiModelProperty(value = "处理状态", required = true, notes = "对应 SystemExceptionLogProcessStatusEnum 枚举类", example = "1")
    private Integer processStatus;

}
