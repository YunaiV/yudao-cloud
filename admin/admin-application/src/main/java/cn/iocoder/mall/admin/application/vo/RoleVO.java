package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("角色 VO")
public class RoleVO {

    @ApiModelProperty(value = "角色编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "角色名字", required = true, example = "系统管理员")
    private String name;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public RoleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleVO setName(String name) {
        this.name = name;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RoleVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}