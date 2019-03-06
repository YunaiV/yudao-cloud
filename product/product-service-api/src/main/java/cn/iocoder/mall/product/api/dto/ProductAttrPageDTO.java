package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotNull;

/**
 * 商品规格分页 DTO
 */
public class ProductAttrPageDTO {

    private String name;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public String getName() {
        return name;
    }

    public ProductAttrPageDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public ProductAttrPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ProductAttrPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

}