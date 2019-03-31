package cn.iocoder.mall.promotion.api.dto;

import javax.validation.constraints.NotNull;

public class ProductRecommendPageDTO {

    /**
     * 推荐类型
     */
    private Integer type;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public ProductRecommendPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public ProductRecommendPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public ProductRecommendPageDTO setType(Integer type) {
        this.type = type;
        return this;
    }

}