package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 商品 Sku 明细 BO
 */
public class AdminsProductSkuDetailVO {

    @ApiModelProperty(value = "sku 编号", required = true, example = "1")
    private Integer id;
    @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
    private Integer spuId;
    @ApiModelProperty(value = "图片地址", required = true, example = "http://www.iocoder.cn")
    private String picURL;
    @ApiModelProperty(value = "规格值数组", required = true)
    private List<AdminsProductAttrAndValuePairVO> attrs;
    @ApiModelProperty(value = "价格，单位：分", required = true, example = "100")
    private Integer price;
    @ApiModelProperty(value = "库存数量", required = true, example = "100")
    private Integer quantity;


    public Integer getId() {
        return id;
    }

    public AdminsProductSkuDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSpuId() {
        return spuId;
    }

    public AdminsProductSkuDetailVO setSpuId(Integer spuId) {
        this.spuId = spuId;
        return this;
    }

    public String getPicURL() {
        return picURL;
    }

    public AdminsProductSkuDetailVO setPicURL(String picURL) {
        this.picURL = picURL;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public AdminsProductSkuDetailVO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AdminsProductSkuDetailVO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public List<AdminsProductAttrAndValuePairVO> getAttrs() {
        return attrs;
    }

    public AdminsProductSkuDetailVO setAttrs(List<AdminsProductAttrAndValuePairVO> attrs) {
        this.attrs = attrs;
        return this;
    }

}