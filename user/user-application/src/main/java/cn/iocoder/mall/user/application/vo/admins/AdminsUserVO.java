package cn.iocoder.mall.user.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户 VO")
public class AdminsUserVO {

    @ApiModelProperty(value = "用户编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "手机号", required = true, example = "15601691300")
    private String mobile;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;
    @ApiModelProperty(value = "账号状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminsUserVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMobile() {
        return mobile;
    }

    public AdminsUserVO setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminsUserVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public AdminsUserVO setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminsUserVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminsUserVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}