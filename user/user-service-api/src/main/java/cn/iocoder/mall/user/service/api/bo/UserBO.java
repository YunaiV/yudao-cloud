package cn.iocoder.mall.user.service.api.bo;

import java.util.Date;

public class UserBO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 账号状态
     *
     * 1 - 开启
     * 2 - 禁用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public UserBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserBO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserBO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserBO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public UserBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}