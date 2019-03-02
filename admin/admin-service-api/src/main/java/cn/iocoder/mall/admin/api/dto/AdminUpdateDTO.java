package cn.iocoder.mall.admin.api.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 管理员更新 DTO
 */
public class AdminUpdateDTO {

    /**
     * 管理员编号
     */
    @NotNull(message = "管理员编号不能为空")
    private Integer id;
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

    public AdminUpdateDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminUpdateDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AdminUpdateDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public AdminUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }
}