package cn.iocoder.mall.product.api.bo;

/**
 * 商品规格明细 BO
 */
public class ProductAttrDetailBO {

    /**
     * 规格编号
     */
    private Integer attrId;
    /**
     * 规格名
     */
    private String attrName;
    /**
     * 规格值
     */
    private Integer attrValueId;
    /**
     * 规格值名
     */
    private String attrValueName;

    public Integer getAttrId() {
        return attrId;
    }

    public ProductAttrDetailBO setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getAttrName() {
        return attrName;
    }

    public ProductAttrDetailBO setAttrName(String attrName) {
        this.attrName = attrName;
        return this;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public ProductAttrDetailBO setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
        return this;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public ProductAttrDetailBO setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
        return this;
    }

}