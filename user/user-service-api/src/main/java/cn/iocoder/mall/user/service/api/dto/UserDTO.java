package cn.iocoder.mall.user.service.api.dto;

public class UserDTO {

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

    public UserDTO setUid(Long uid) {
        this.uid = uid;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDTO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

}