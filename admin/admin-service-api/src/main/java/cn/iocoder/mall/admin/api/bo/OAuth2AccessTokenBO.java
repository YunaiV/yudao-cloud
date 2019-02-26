package cn.iocoder.mall.admin.api.bo;

import java.io.Serializable;

public class OAuth2AccessTokenBO implements Serializable {

    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 过期时间，单位：秒。
     */
    private Integer expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public OAuth2AccessTokenBO setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public OAuth2AccessTokenBO setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public OAuth2AccessTokenBO setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

}