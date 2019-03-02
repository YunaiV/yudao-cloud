package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("管理员拥有的角色 VO")
public class AdminRoleVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名字", required = true, example = "系统管理员")
    private String name;
    @ApiModelProperty(value = "是否授权", required = true, example = "true")
    private Boolean assigned;

    public Integer getId() {
        return id;
    }

    public AdminRoleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminRoleVO setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public AdminRoleVO setAssigned(Boolean assigned) {
        this.assigned = assigned;
        return this;
    }

}