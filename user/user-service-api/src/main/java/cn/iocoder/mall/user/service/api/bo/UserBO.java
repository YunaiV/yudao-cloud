package cn.iocoder.mall.user.service.api.bo;

public class UserBO {

    /**
     * 用户编号
     */
    private Long uid;
    /**
     * 手机号
     */
    private String mobile;

    public Long getUid() {
        return uid;
    }

    public UserBO setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserBO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

}