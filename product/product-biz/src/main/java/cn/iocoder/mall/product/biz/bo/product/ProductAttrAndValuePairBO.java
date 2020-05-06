package cn.iocoder.mall.product.biz.bo.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品规格明细 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrAndValuePairBO implements Serializable {

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

}
