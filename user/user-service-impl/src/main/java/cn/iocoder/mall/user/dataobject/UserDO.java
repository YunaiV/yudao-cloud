package cn.iocoder.mall.user.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 用户实体，存储用户基本数据。
 *
 * idx_mobile 唯一索引
 */
public class UserDO extends BaseDO {

    /**
     * 用户编号
     */
    private Long id;
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

    public Long getId() {
        return id;
    }

    public UserDO setId(Long id) {
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

}