package cn.iocoder.mall.product.api.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品规格明细 VO
 */
public class ProductAttrDetailBO implements Serializable {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 规格名
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
    /**
     * 规格值数组
     */
    private List<ProductAttrValueDetailBO> values;

    public Integer getId() {
        return id;
    }

    public ProductAttrDetailBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrDetailBO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrDetailBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductAttrDetailBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public List<ProductAttrValueDetailBO> getValues() {
        return values;
    }

    public ProductAttrDetailBO setValues(List<ProductAttrValueDetailBO> values) {
        this.values = values;
        return this;
    }
}