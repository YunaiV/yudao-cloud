package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

@ApiModel("管理员信息 VO")
public class AdminInfoVO {

    @ApiModelProperty(value = "管理员比那好", required = true, example = "1")
    private Integer adminId;
    @ApiModelProperty(value = "角色编号的数组", required = true, example = "[1, 2]")
    private Set<Integer> roleIds;

    public Integer getAdminId() {
        return adminId;
    }

    public AdminInfoVO setAdminId(Integer adminId) {
        this.adminId = adminId;
        return this;
    }

    public Set<Integer> getRoleIds() {
        return roleIds;
    }

    public AdminInfoVO setRoleIds(Set<Integer> roleIds) {
        this.roleIds = roleIds;
        return this;
    }

}