package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品规格精简 VO", description = "带有规格值数组")
public class AdminsProductAttrSimpleVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String name;
    @ApiModelProperty(value = "规格值数组", required = true)
    private List<AdminsProductAttrValueSimpleVO> values;

    public Integer getId() {
        return id;
    }

    public AdminsProductAttrSimpleVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductAttrSimpleVO setName(String name) {
        this.name = name;
        return this;
    }

    public List<AdminsProductAttrValueSimpleVO> getValues() {
        return values;
    }

    public AdminsProductAttrSimpleVO setValues(List<AdminsProductAttrValueSimpleVO> values) {
        this.values = values;
        return this;
    }

}