package cn.iocoder.mall.promotion.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("Banner VO")
public class BannerVO {

    @ApiModelProperty(value = "Banner 编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "标题", required = true, example = "活动 A")
    private String title;
    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.baidu.com")
    private String url;
    @ApiModelProperty(value = "突脸链接", required = true, example = "http://www.iocoder.cn/01.jpg")
    private String picUrl;
    @ApiModelProperty(value = "排序", required = true, example = "10")
    private Integer sort;
    @ApiModelProperty(value = "状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "备注", required = true, example = "这个活动很牛逼")
    private String memo;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public BannerVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerVO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BannerVO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public BannerVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public BannerVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public BannerVO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public BannerVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public BannerVO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
}