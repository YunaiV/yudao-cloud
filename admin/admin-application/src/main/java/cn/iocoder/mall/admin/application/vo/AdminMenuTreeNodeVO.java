package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员拥有的菜单 VO")
public class AdminMenuTreeNodeVO {

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
    private List<AdminMenuTreeNodeVO> children;

    public Integer getId() {
        return id;
    }

    public AdminMenuTreeNodeVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public AdminMenuTreeNodeVO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public AdminMenuTreeNodeVO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public AdminMenuTreeNodeVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public AdminMenuTreeNodeVO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public List<AdminMenuTreeNodeVO> getChildren() {
        return children;
    }

    public AdminMenuTreeNodeVO setChildren(List<AdminMenuTreeNodeVO> children) {
        this.children = children;
        return this;
    }

}