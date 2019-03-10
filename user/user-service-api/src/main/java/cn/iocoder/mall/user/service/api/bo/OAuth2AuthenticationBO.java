package cn.iocoder.mall.user.service.api.bo;

import java.io.Serializable;

public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 用户编号
     */
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public OAuth2AuthenticationBO setUid(Integer uid) {
        this.uid = uid;
        return this;
    }

}