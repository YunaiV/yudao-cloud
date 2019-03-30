package cn.iocoder.mall.promotion.api.bo;

import java.util.Date;

/**
 * Banner BO
 */
public class BannerBO {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 跳转链接
     */
    private String url;
    /**
     * 图片链接
     */
    private String picUrl;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public BannerBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerBO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BannerBO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public BannerBO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BannerBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public BannerBO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BannerBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public BannerBO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
}