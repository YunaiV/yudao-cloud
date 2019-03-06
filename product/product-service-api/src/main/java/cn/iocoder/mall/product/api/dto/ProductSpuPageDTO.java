package cn.iocoder.mall.product.api.dto;

import javax.validation.constraints.NotNull;

public class ProductSpuPageDTO {

    private String name;

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

}