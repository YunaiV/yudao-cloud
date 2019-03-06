package cn.iocoder.mall.product.application.vo.admins;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "商品 SPU VO", description = "不包括 SKU 信息 VO")
public class AdminsProductSpuVO {

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

    public Integer getId() {
        return id;
    }

    public AdminsProductSpuVO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AdminsProductSpuVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public AdminsProductSpuVO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AdminsProductSpuVO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public AdminsProductSpuVO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public AdminsProductSpuVO setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public AdminsProductSpuVO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public AdminsProductSpuVO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }

}