package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("商品推荐分页 VO")
public class AdminsProductRecommendPageVO {

    @ApiModelProperty(value = "商品推荐数组")
    private List<AdminsProductRecommendVO> list;
    @ApiModelProperty(value = "商品推荐总数")
    private Integer total;

    public List<AdminsProductRecommendVO> getList() {
        return list;
    }

    public AdminsProductRecommendPageVO setList(List<AdminsProductRecommendVO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AdminsProductRecommendPageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}