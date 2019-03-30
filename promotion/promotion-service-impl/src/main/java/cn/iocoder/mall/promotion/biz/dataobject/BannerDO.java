package cn.iocoder.mall.promotion.biz.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * Banner 广告页
 */
public class BannerDO extends DeletableDO {

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
     *
     * {@link cn.iocoder.common.framework.constant.CommonStatusEnum}
     */
    private Integer status;
    /**
     * 备注
     */
    private String memo;

    // TODO 芋艿 点击次数。&& 其他数据相关

    public Integer getId() {
        return id;
    }

    public BannerDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerDO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BannerDO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BannerDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public BannerDO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public BannerDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public BannerDO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

}