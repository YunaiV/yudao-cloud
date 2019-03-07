package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotNull;

/**
 * 商品 Spu 分页 DTO
 */
public class ProductSpuPageDTO {

    /**
     * 商品名
     *
     * 模糊匹配
     */
    private String name;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 是否可见
     */
    private Boolean visible;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public ProductSpuPageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public ProductSpuPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ProductSpuPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuPageDTO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuPageDTO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

}