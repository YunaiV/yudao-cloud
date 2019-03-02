package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;

/**
 * 管理员添加 DTO
 */
public class AdminAddDTO {

    /**
     * 登陆账号
     */
    @NotEmpty(message = "登陆账号不能为空")
    private String username;
    /**
     * 昵称
     */
    @NotEmpty(message = "昵称不能为空")
    private String nickname;
    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public AdminAddDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminAddDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminAddDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}