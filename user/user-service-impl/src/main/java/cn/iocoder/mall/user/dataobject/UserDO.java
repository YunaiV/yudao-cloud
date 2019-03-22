package cn.iocoder.mall.user.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * 用户实体，存储用户基本数据。
 *
 * idx_mobile 唯一索引
 */
public class UserDO extends DeletableDO {

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

    public Integer getId() {
        return id;
    }

    public UserDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UserDO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserDO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}