package cn.iocoder.mall.promotion.api.dto;

import javax.validation.constraints.NotNull;

/**
 * 优惠劵模板分页 DTO
 */
public class CouponTemplatePageDTO {

    /**
     * 类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 优惠类型
     */
    private Integer preferentialType;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getType() {
        return type;
    }

    public CouponTemplatePageDTO setType(Integer type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CouponTemplatePageDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CouponTemplatePageDTO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getPreferentialType() {
        return preferentialType;
    }

    public CouponTemplatePageDTO setPreferentialType(Integer preferentialType) {
        this.preferentialType = preferentialType;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public CouponTemplatePageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public CouponTemplatePageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }
}
