package cn.iocoder.mall.product.api.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品规格值 VO
 */
public class ProductAttrValueBO implements Serializable {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格编号
     */
    private Integer attrId;
    /**
     * 规格值名
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public ProductAttrValueBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrValueBO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrValueBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductAttrValueBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public ProductAttrValueBO setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }
}