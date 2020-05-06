package cn.iocoder.mall.product.biz.bo.product;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格精简 VO
 */
@Data
@Accessors(chain = true)
public class ProductAttrSimpleBO implements Serializable {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 规格名
     */
    private String name;
    /**
     * 规格值数组
     */
    private List<ProductAttrValueSimpleBO> values;

}
