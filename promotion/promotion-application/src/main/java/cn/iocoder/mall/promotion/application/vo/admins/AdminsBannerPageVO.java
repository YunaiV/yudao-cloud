package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Banner 分页 VO")
public class AdminsBannerPageVO {

    @ApiModelProperty(value = "Banner 数组")
    private List<AdminsBannerVO> list;
    @ApiModelProperty(value = "Banner 总数")
    private Integer total;

    public List<AdminsBannerVO> getList() {
        return list;
    }

    public AdminsBannerPageVO setList(List<AdminsBannerVO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AdminsBannerPageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}