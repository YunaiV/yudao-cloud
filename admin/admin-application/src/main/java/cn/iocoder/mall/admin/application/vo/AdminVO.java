package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("管理员 VO")
public class AdminVO {

    @ApiModelProperty(value = "管理员编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "登陆账号", required = true, example = "15601691300")
    private String username;
    @ApiModelProperty(value = "昵称", required = true, example = "小王")
    private String nickname;
    @ApiModelProperty(value = "账号状态", required = true, example = "1")
    private Integer status;
    @ApiModelProperty(value = "创建时间", required = true, example = "时间戳格式")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public AdminVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AdminVO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminVO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public AdminVO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public AdminVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

}