package cn.iocoder.mall.product.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格明细分页 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrPageBO implements Serializable {

    /**
     * 规格数组
     */
    private List<ProductAttrDetailBO> attrs;
    /**
     * 总数
     */
    private Integer count;

}
