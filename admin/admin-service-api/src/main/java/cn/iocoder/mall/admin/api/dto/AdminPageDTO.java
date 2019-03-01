package cn.iocoder.mall.admin.api.dto;

public class AdminPageDTO {

    private String nickname;

    private Integer pageNo;
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public AdminPageDTO setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public AdminPageDTO setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public AdminPageDTO setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

}
