package cn.iocoder.mall.user.service.api.bo;

import java.util.List;

public class UserPageBO {

    /**
     * 用户数组
     */
    private List<UserBO> users;
    /**
     * 总量
     */
    private Integer count;

    public List<UserBO> getUsers() {
        return users;
    }

    public UserPageBO setUsers(List<UserBO> users) {
        this.users = users;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public UserPageBO setCount(Integer count) {
        this.count = count;
        return this;
    }

}