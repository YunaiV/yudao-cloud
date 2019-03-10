package cn.iocoder.mall.user.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户信息 VO")
public class UsersUserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "123")
    private Integer id;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;

    public Integer getId() {
        return id;
    }

    public UsersUserVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public UsersUserVO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public UsersUserVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UsersUserVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

}