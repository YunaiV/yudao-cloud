package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * 商品分类
 */
public class ProductCategoryDO extends DeletableDO {

    /**
     * 分类编号
     */
    private Integer id;
    /**
     * 父分类编号
     *
     * 如果不存在父级，则 pid = 0 。
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类图片
     */
    private String picUrl;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 状态
     *
     * 1-开启
     * 2-关闭
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public ProductCategoryDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public ProductCategoryDO setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductCategoryDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCategoryDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public ProductCategoryDO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductCategoryDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductCategoryDO setStatus(Integer status) {
        this.status = status;
        return this;
    }
}