package cn.iocoder.mall.product.biz.bo.product;

import cn.iocoder.mall.product.biz.bo.brand.ProductBrandBO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品品牌分页 BO
 */
@Data
@Accessors(chain = true)
public class ProductBrangPageBO implements Serializable {

    /**
     * 品牌数组
     */
    private List<ProductBrandBO> brands;
    /**
     * 总数
     */
    private Integer count;

}
