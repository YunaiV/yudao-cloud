package cn.iocoder.mall.promotion.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("Banner 分页 VO")
public class BannerPageVO {

    @ApiModelProperty(value = "Banner 数组")
    private List<BannerVO> list;
    @ApiModelProperty(value = "Banner 总数")
    private Integer total;

    public List<BannerVO> getList() {
        return list;
    }

    public BannerPageVO setList(List<BannerVO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public BannerPageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}