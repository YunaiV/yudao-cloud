package cn.iocoder.mall.user.service.api.dto;

import javax.validation.constraints.NotNull;

public class UserPageDTO {

    /**
     * 查询的昵称
     *
     * 模糊查询
     */
    private String nickname;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public String getNickname() {
        return nickname;
    }

    public UserPageDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public UserPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

}