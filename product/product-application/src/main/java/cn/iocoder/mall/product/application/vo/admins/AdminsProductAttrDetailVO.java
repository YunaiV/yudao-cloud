package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "商品规格 VO")
public class AdminsProductAttrDetailVO {

    @ApiModelProperty(value = "规格编号", required = true, example = "1")
    private Integer attrId;
    @ApiModelProperty(value = "规格名", required = true, example = "颜色")
    private String attrName;
    @ApiModelProperty(value = "规格值", required = true, example = "10")
    private Integer attrValueId;
    @ApiModelProperty(value = "规格值名", required = true, example = "红色")
    private String attrValueName;

    public Integer getAttrId() {
        return attrId;
    }

    public AdminsProductAttrDetailVO setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getAttrName() {
        return attrName;
    }

    public AdminsProductAttrDetailVO setAttrName(String attrName) {
        this.attrName = attrName;
        return this;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public AdminsProductAttrDetailVO setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
        return this;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public AdminsProductAttrDetailVO setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
        return this;
    }

}