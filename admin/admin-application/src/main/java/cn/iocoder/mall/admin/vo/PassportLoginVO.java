package cn.iocoder.mall.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登陆结果 VO")
public class PassportLoginVO {

    @ApiModelProperty(value = "访问令牌", required = true, example = "2e3d7635c15e47e997611707a237859f")
    private String accessToken;
    @ApiModelProperty(value = "刷新令牌", required = true, example = "d091e7c35bbb4313b0f557a6ef23d033")
    private String refreshToken;
    @ApiModelProperty(value = "过期时间，单位：秒", required = true, example = "2879")
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public PassportLoginVO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public PassportLoginVO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public PassportLoginVO setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}