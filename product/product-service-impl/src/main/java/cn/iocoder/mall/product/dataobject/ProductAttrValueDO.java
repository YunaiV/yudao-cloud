package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;

/**
 * Product 规格值
 */
public class ProductAttrValueDO extends DeletableDO {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格编号
     */
    private Integer attrId;
    /**
     * 规格值
     */
    private String name;
    /**
     * 状态
     *
     * 1-正常
     * 2-禁用
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public ProductAttrValueDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public ProductAttrValueDO setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrValueDO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrValueDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}