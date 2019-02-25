package cn.iocoder.mall.user.service.api.bo;

import java.io.Serializable;

public class OAuth2AuthenticationBO implements Serializable {

    /**
     * 用户编号
     */
    private Long uid;

    public Long getUid() {
        return uid;
    }

    public OAuth2AuthenticationBO setUid(Long uid) {
        this.uid = uid;
        return this;
    }

}