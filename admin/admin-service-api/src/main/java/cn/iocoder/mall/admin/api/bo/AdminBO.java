package cn.iocoder.mall.admin.api.bo;

import java.util.Date;

public class AdminBO {

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
     * 账号状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminBO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminBO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
