package cn.iocoder.mall.product.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("产品分类树节点 VO")
public class ProductCategoryTreeNodeVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "父分类编号", required = true, example = "0")
    private Integer pid;
    @ApiModelProperty(value = "分类名", required = true, example = "手机")
    private String name;
    @ApiModelProperty(value = "描述", required = true, example = "这个商品很吊")
    private String description;
    @ApiModelProperty(value = "分类图片", notes = "一般情况下，只有根分类才有图片", example = "http://www.iocoder.cn/images/common/wechat_mp_2017_07_31_bak.jpg")
    private String picUrl;
    @ApiModelProperty(value = "排序值", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, notes = "1-开启；2-关闭", example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳")
    private Date createTime;
    @ApiModelProperty(value = "子节点数组")
    private List<ProductCategoryTreeNodeVO> children;

    public Integer getId() {
        return id;
    }

    public ProductCategoryTreeNodeVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ProductCategoryTreeNodeVO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryTreeNodeVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategoryTreeNodeVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ProductCategoryTreeNodeVO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductCategoryTreeNodeVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductCategoryTreeNodeVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductCategoryTreeNodeVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<ProductCategoryTreeNodeVO> getChildren() {
        return children;
    }

    public ProductCategoryTreeNodeVO setChildren(List<ProductCategoryTreeNodeVO> children) {
        this.children = children;
        return this;
    }

}