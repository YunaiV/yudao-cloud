package cn.iocoder.mall.promotion.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("优惠劵（码）分页 VO")
public class AdminsCouponTemplatePageVO {

    @ApiModelProperty(value = "优惠劵（码）数组")
    private List<AdminsCouponTemplateVO> list;
    @ApiModelProperty(value = "优惠劵（码）总数")
    private Integer total;

    public List<AdminsCouponTemplateVO> getList() {
        return list;
    }

    public AdminsCouponTemplatePageVO setList(List<AdminsCouponTemplateVO> list) {
        this.list = list;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public AdminsCouponTemplatePageVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

}
