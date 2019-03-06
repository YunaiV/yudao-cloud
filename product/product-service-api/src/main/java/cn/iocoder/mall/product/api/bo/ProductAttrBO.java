package cn.iocoder.mall.product.api.bo;

import java.util.Date;

/**
 * 商品规格 VO
 */
public class ProductAttrBO {

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

    public Integer getId() {
        return id;
    }

    public ProductAttrBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrBO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductAttrBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}