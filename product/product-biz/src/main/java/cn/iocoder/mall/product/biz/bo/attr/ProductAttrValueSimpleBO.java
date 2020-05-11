package cn.iocoder.mall.product.biz.bo.attr;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品规格值 VO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueSimpleBO implements Serializable {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格值名
     */
    private String name;

}
