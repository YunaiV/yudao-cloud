package cn.iocoder.mall.promotion.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Banner VO")
public class UsersBannerVO {

    @ApiModelProperty(value = "跳转链接", required = true, example = "http://www.baidu.com")
    private String url;
    @ApiModelProperty(value = "图片链接", required = true, example = "http://www.iocoder.cn/01.jpg")
    private String picUrl;

    public String getUrl() {
        return url;
    }

    public UsersBannerVO setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public UsersBannerVO setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }

}