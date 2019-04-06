package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@ApiModel("角色分页 VO")
@Data
@Accessors(chain = true)
public class RolePageVO {

    @ApiModelProperty(value = "角色数组")
    private List<RoleVO> roles;
    @ApiModelProperty(value = "角色总数")
    private Integer count;

}
