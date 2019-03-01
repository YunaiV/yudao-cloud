package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员分页 VO")
public class AdminPageVO {

    @ApiModelProperty(value = "管理员数组")
    private List<AdminVO> admins;
    @ApiModelProperty(value = "管理员总数")
    private Integer count;

    public List<AdminVO> getAdmins() {
        return admins;
    }

    public AdminPageVO setAdmins(List<AdminVO> admins) {
        this.admins = admins;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public AdminPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}