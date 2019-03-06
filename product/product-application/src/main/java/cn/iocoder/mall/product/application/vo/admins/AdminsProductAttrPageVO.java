package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品规格明细分页 VO")
public class AdminsProductAttrPageVO {

    @ApiModelProperty(value = "规格数组", required = true)
    private List<AdminsProductAttrDetailVO> attrs;
    @ApiModelProperty(value = "总数", required = true)
    private Integer count;

    public List<AdminsProductAttrDetailVO> getAttrs() {
        return attrs;
    }

    public AdminsProductAttrPageVO setAttrs(List<AdminsProductAttrDetailVO> attrs) {
        this.attrs = attrs;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public AdminsProductAttrPageVO setCount(Integer count) {
        this.count = count;
        return this;
    }

}