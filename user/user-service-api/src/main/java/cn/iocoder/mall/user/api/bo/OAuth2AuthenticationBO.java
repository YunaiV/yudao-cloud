package cn.iocoder.mall.user.api.bo;

import java.io.Serializable;

public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public OAuth2AuthenticationBO setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

}