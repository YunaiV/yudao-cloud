package cn.iocoder.mall.product.application.vo.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品 SPU 详细 VO", description = "包括 SKU 信息 VO")
public class UsersProductSpuDetailVO {

    @ApiModelProperty(value = "SPU 编号", required = true, example = "1")
    private Integer id;

    // ========== 基本信息 =========
    @ApiModelProperty(value = "SPU 名字", required = true, example = "厮大牛逼")
    private String name;
    @ApiModelProperty(value = "卖点", required = true, example = "各种 MQ 骚操作")
    private String sellPoint;
    @ApiModelProperty(value = "描述", required = true, example = "你就说强不强")
    private String description;
    @ApiModelProperty(value = "分类编号", required = true, example = "反正我是信了")
    private Integer cid;
    @ApiModelProperty(value = "商品主图地址的数组", required = true, example = "http://www.iocoder.cn")
    private List<String> picUrls;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    private List<UsersProductSkuDetailVO> skus;

    public Integer getId() {
        return id;
    }

    public UsersProductSpuDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UsersProductSpuDetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public UsersProductSpuDetailVO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UsersProductSpuDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public UsersProductSpuDetailVO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public UsersProductSpuDetailVO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public List<UsersProductSkuDetailVO> getSkus() {
        return skus;
    }

    public UsersProductSpuDetailVO setSkus(List<UsersProductSkuDetailVO> skus) {
        this.skus = skus;
        return this;
    }
}