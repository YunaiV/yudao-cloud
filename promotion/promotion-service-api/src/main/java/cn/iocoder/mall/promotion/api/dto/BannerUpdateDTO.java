package cn.iocoder.mall.promotion.api.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Banner 更新 DTO
 */
public class BannerUpdateDTO {

    @NotNull(message = "编号不能为空")
    private Integer id;
    @NotEmpty(message = "标题不能为空")
    @Length(min = 2, max = 32, message = "标题长度为 2-32 位")
    private String title;
    @NotEmpty(message = "跳转链接不能为空")
    @URL(message = "跳转链接格式不正确")
    @Length(max = 255, message = "跳转链接最大长度为 255 位")
    private String url;
    @NotEmpty(message = "图片链接不能为空")
    @URL(message = "图片链接格式不正确")
    @Length(max = 255, message = "图片链接最大长度为 255 位")
    private String picUrl;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    @Length(max = 255, message = "备注最大长度为 255 位")
    private String memo;

    public Integer getId() {
        return id;
    }

    public BannerUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BannerUpdateDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BannerUpdateDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public BannerUpdateDTO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public String getMemo() {
        return memo;
    }

    public BannerUpdateDTO setMemo(String memo) {
        this.memo = memo;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public BannerUpdateDTO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

}