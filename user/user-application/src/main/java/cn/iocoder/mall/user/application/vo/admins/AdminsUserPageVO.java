package cn.iocoder.mall.user.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("用户分页 VO")
public class AdminsUserPageVO {

    @ApiModelProperty(value = "用户数组")
    private List<AdminsUserVO> users;
    @ApiModelProperty(value = "用户总数")
    private Integer count;

    public List<AdminsUserVO> getUsers() {
        return users;
    }

    public AdminsUserPageVO setUsers(List<AdminsUserVO> users) {
        this.users = users;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public AdminsUserPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}