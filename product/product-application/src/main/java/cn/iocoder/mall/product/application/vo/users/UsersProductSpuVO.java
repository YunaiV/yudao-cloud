package cn.iocoder.mall.product.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品 SPU VO", description = "不包括 SKU 信息 VO")
public class UsersProductSpuVO {

    @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
    private Integer id;

    // ========== 基本信息 =========
    @ApiModelProperty(value = "SPU 名字", required = true, example = "厮大牛逼")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "各种 MQ 骚操作")
    private String sellPoint;
    @ApiModelProperty(value = "分类编号", required = true, example = "反正我是信了")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址的数组", required = true, example = "http://www.iocoder.cn")
    private List<String> picUrls;

    // ========== Sku 相关字段 =========
    /**
     * 价格
     *
     * 目前的计算方式是，以 Sku 最小价格为准
     */
    private Integer price;
    /**
     * 库存数量
     *
     * 目前的计算方式是，以 Sku 库存累加为准
     */
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public UsersProductSpuVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UsersProductSpuVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public UsersProductSpuVO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public UsersProductSpuVO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public UsersProductSpuVO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public UsersProductSpuVO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public UsersProductSpuVO setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}