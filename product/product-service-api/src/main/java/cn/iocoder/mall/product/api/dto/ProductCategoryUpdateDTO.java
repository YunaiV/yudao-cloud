package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotNull;

/**
 * 商品分类更新 DTO
 */
public class ProductCategoryUpdateDTO {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;
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
    @NotNull(message = "分类图片不能为空")
    private String picUrl;
    /**
     * 排序值
     */
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    public Integer getPid() {
        return pid;
    }

    public ProductCategoryUpdateDTO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategoryUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ProductCategoryUpdateDTO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductCategoryUpdateDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductCategoryUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}