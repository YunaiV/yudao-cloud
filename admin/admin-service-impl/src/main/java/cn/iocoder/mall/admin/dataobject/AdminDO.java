package cn.iocoder.mall.admin.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * 管理员实体
 */
public class AdminDO extends DeletableDO {

    /**
     * 管理员编号
     */
    private Integer id;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     *
     * TODO 芋艿 暂时最简单的 MD5
     */
    private String password;
    /**
     * 账号状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public AdminDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminDO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminDO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminDO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}
