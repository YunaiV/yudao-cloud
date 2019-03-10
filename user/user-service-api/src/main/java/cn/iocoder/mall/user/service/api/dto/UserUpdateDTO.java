package cn.iocoder.mall.user.service.api.dto;

/**
 * 用户更新 DTO
 */
public class UserUpdateDTO {

    /**
     * 用户编号
     */
    private Integer id;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;

    public Integer getId() {
        return id;
    }

    public UserUpdateDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UserUpdateDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserUpdateDTO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

}