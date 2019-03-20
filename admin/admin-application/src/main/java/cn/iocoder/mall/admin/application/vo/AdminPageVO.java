package cn.iocoder.mall.admin.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("管理员分页 VO")
public class AdminPageVO {

    @ApiModelProperty(value = "管理员数组")
    private List<AdminVO> list;
    @ApiModelProperty(value = "管理员总数")
    private Integer total;

    public List<AdminVO> getList() {
        return list;
    }

    public AdminPageVO setList(List<AdminVO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AdminPageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}