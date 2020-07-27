package cn.iocoder.mall.productservice.service.attr.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品规格键值对 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyValueBO implements Serializable {

    /**
     * 规格 Key 编号
     */
    private Integer attrKeyId;
    /**
     * 规格 Key 名字
     */
    private String attrKeyName;
    /**
     * 规格 Value 编号
     */
    private Integer attrValueId;
    /**
     * 规格 Value 名字
     */
    private String attrValueName;

}
