package cn.iocoder.mall.product.dataobject;

import java.util.Date;

/**
 * 商品 SPU
 */
public class ProductSpuDO {

    // ========== 基础字段 =========

    /**
     * SPU 编号
     */
    private Integer id;

    // TODO 店铺编号

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date updateTime;
    /**
     * 状态
     *
     * 1-正常
     * 2-删除
     */
    private Integer status;

    // ========== 基本信息 =========
    /**
     * SPU 名字
     */
    private String name;
    /**
     * SPU 描述
     */
    private String descrption;
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
    private String picURLs;

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
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}