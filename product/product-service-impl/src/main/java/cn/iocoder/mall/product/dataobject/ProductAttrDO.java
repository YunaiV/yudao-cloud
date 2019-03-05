package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.BaseDO;

/**
 * Product 规格
 */
public class ProductAttrDO extends BaseDO {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     *
     * 1-开启
     * 2-禁用
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public ProductAttrDO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrDO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrDO setStatus(Integer status) {
        this.status = status;
        return this;
    }

}