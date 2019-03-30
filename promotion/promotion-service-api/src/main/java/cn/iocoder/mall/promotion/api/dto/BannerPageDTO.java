package cn.iocoder.mall.promotion.api.dto;

import javax.validation.constraints.NotNull;

public class BannerPageDTO {

    /**
     * 标题，模糊匹配
     */
    private String title;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public BannerPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public BannerPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerPageDTO setTitle(String title) {
        this.title = title;
        return this;
    }

}