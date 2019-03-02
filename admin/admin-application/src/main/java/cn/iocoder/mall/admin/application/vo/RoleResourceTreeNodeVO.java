package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("角色拥有的资源 VO")
public class RoleResourceTreeNodeVO {

    @ApiModelProperty(value = "菜单编号", required = true, example = "1")
    private Integer id;
//    @ApiModelProperty(value = "菜单名", required = true, example = "商品管理")
//    private String name;
    @ApiModelProperty(value = "菜单操作", required = true, example = "/order/list")
    private String handler;
    @ApiModelProperty(value = "父菜单编号", required = true, example = "1", notes = "如果无父菜单，则值为 0")
    private Integer pid;
    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;
    @ApiModelProperty(value = "菜单展示名", required = true, example = "商品管理")
    private String displayName;
    @ApiModelProperty(value = "子节点数组")
    private List<RoleResourceTreeNodeVO> children;
    @ApiModelProperty(value = "是否授权", required = true, example = "true")
    private Boolean assigned;

    public Integer getId() {
        return id;
    }

    public RoleResourceTreeNodeVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public RoleResourceTreeNodeVO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public RoleResourceTreeNodeVO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public RoleResourceTreeNodeVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public RoleResourceTreeNodeVO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public List<RoleResourceTreeNodeVO> getChildren() {
        return children;
    }

    public RoleResourceTreeNodeVO setChildren(List<RoleResourceTreeNodeVO> children) {
        this.children = children;
        return this;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public RoleResourceTreeNodeVO setAssigned(Boolean assigned) {
        this.assigned = assigned;
        return this;
    }
}