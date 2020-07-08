package cn.iocoder.mall.managementweb.controller.permission.dto;

import cn.iocoder.common.framework.vo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("角色分页 DTO")
@Data
public class RolePageDTO extends PageParam {

    @ApiModelProperty(value = "角色名", example = "管理", notes = "模糊匹配")
    private String name;

}
