package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * 商品 SPU
 */
public class ProductSpuDO extends BaseDO {

    /**
     * SPU 编号
     */
    private Integer id;

    // TODO 店铺编号 先不考虑，因为第一个版本，不做 B2B2C

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    private String name;
    /**
     * 卖点
     */
    private String sellPoint;
    /**
     * 描述
     */
    private String description;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 商品主图地址
     *
     * 数组，以逗号分隔
     *
     * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张
     */
    private String picUrls;

    // TODO 价格库存

    // TODO 运费信息

    // ========== 其他信息 =========
    /**
     * 是否上架商品（是否可见）。
     *
     * true 为已上架
     * false 为已下架
     */
    private Boolean visible;
    /**
     * 排序字段
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public ProductSpuDO setName(String name) {
        this.name = name;
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public ProductSpuDO setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductSpuDO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getCid() {
        return cid;
    }

    public ProductSpuDO setCid(Integer cid) {
        this.cid = cid;
        return this;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public ProductSpuDO setPicUrls(String picUrls) {
        this.picUrls = picUrls;
        return this;
    }

    public Boolean getVisible() {
        return visible;
    }

    public ProductSpuDO setVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public Integer getSort() {
        return sort;
    }

    public ProductSpuDO setSort(Integer sort) {
        this.sort = sort;
        return this;
    }



}