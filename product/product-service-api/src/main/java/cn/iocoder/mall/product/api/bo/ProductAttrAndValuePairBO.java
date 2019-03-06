package cn.iocoder.mall.product.api.bo;

/**
 * 商品规格明细 BO
 */
public class ProductAttrAndValuePairBO {

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

    public ProductAttrAndValuePairBO setAttrId(Integer attrId) {
        this.attrId = attrId;
        return this;
    }

    public String getAttrName() {
        return attrName;
    }

    public ProductAttrAndValuePairBO setAttrName(String attrName) {
        this.attrName = attrName;
        return this;
    }

    public Integer getAttrValueId() {
        return attrValueId;
    }

    public ProductAttrAndValuePairBO setAttrValueId(Integer attrValueId) {
        this.attrValueId = attrValueId;
        return this;
    }

    public String getAttrValueName() {
        return attrValueName;
    }

    public ProductAttrAndValuePairBO setAttrValueName(String attrValueName) {
        this.attrValueName = attrValueName;
        return this;
    }

}