package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品 SPU 详细 VO", description = "包括 SKU 信息 VO")
public class AdminsProductSpuDetailVO {

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

    // ========== 其他信息 =========
    @ApiModelProperty(value = "是否上架商品（是否可见）", required = true, example = "true")
    private Boolean visible;
    @ApiModelProperty(value = "排序字段", required = true, example = "10")
    private Integer sort;

    // ========== SKU =========

    /**
     * SKU 数组
     */
    private List<AdminsProductSkuDetailVO> skus;

    public Integer getId() {
        return id;
    }

    public AdminsProductSpuDetailVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductSpuDetailVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public AdminsProductSpuDetailVO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AdminsProductSpuDetailVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public AdminsProductSpuDetailVO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public AdminsProductSpuDetailVO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public AdminsProductSpuDetailVO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public AdminsProductSpuDetailVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public List<AdminsProductSkuDetailVO> getSkus() {
        return skus;
    }

    public AdminsProductSpuDetailVO setSkus(List<AdminsProductSkuDetailVO> skus) {
        this.skus = skus;
        return this;
    }

}