package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品规格值精简 VO")
public class AdminsProductAttrValueSimpleVO {

    @ApiModelProperty(value = "规格值编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格值名", required = true, example = "颜色")
    private String name;

    public Integer getId() {
        return id;
    }

    public AdminsProductAttrValueSimpleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductAttrValueSimpleVO setName(String name) {
        this.name = name;
        return this;
    }

}