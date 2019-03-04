package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotNull;

/**
 * 商品分类添加 DTO
 */
public class ProductCategoryAddDTO {

    /**
     * 父分类编号
     */
    @NotNull(message = "父分类编号不能为空")
    private Integer pid;
    /**
     * 名称
     */
    @NotNull(message = "名称不能为空")
    private String name;
    /**
     * 描述
     */
    @NotNull(message = "描述不能为空")
    private String description;
    /**
     * 分类图片
     */
//    @NotNull(message = "分类图片不能为空")
    private String picUrl;
    /**
     * 排序值
     */
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    public Integer getPid() {
        return pid;
    }

    public ProductCategoryAddDTO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategoryAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ProductCategoryAddDTO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductCategoryAddDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

}