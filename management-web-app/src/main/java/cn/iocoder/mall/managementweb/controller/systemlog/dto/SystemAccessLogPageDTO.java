package cn.iocoder.mall.managementweb.controller.systemlog.dto;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("系统访问日志分页 DTO")
@Data
public class SystemAccessLogPageDTO extends PageParam {

    @ApiModelProperty(value = "用户编号", example = "1")
    private Integer userId;
    @ApiModelProperty(value = "用户类型", example = "2")
    private Integer userType;
    @ApiModelProperty(value = "应用名", example = "xxx-service-application")
    private String applicationName;

}
