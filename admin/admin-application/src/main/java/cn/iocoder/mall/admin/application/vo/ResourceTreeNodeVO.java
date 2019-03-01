package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("资源树结构 VO")
public class ResourceTreeNodeVO {

    @ApiModelProperty(value = "资源编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "资源名字（标识）", required = true, example = "商品管理")
    private String name;
    @ApiModelProperty(value = "资源类型", required = true, example = "1")
    private Integer type;
    @ApiModelProperty(value = "排序", required = true, example = "1")
    private Integer sort;
    @ApiModelProperty(value = "菜单展示名", required = true, example = "商品管理")
    private String displayName;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;
    @ApiModelProperty(value = "父级资源编号", required = true, example = "1", notes = "如果无父资源，则值为 0")
    private Integer pid;
    @ApiModelProperty(value = "操作", required = true, example = "/order/list")
    private String handler;
    @ApiModelProperty(value = "子节点数组")
    private List<ResourceTreeNodeVO> children;

    public Integer getId() {
        return id;
    }

    public ResourceTreeNodeVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ResourceTreeNodeVO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ResourceTreeNodeVO setType(Integer type) {
        this.type = type;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ResourceTreeNodeVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceTreeNodeVO setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ResourceTreeNodeVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ResourceTreeNodeVO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getHandler() {
        return handler;
    }

    public ResourceTreeNodeVO setHandler(String handler) {
        this.handler = handler;
        return this;
    }

    public List<ResourceTreeNodeVO> getChildren() {
        return children;
    }

    public ResourceTreeNodeVO setChildren(List<ResourceTreeNodeVO> children) {
        this.children = children;
        return this;
    }

}