package cn.iocoder.mall.product.api.bo;

import java.util.Date;

/**
 * 商品规格值 VO
 */
public class ProductAttrValueDetailBO {

    /**
     * 规格值编号
     */
    private Integer id;
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

    public ProductAttrValueDetailBO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductAttrValueDetailBO setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ProductAttrValueDetailBO setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductAttrValueDetailBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}