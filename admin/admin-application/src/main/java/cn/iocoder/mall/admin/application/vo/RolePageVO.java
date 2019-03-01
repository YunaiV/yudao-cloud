package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("角色分页 VO")
public class RolePageVO {

    @ApiModelProperty(value = "角色数组")
    private List<RoleVO> roles;
    @ApiModelProperty(value = "角色总数")
    private Integer count;

    public List<RoleVO> getRoles() {
        return roles;
    }

    public RolePageVO setRoles(List<RoleVO> roles) {
        this.roles = roles;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public RolePageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}