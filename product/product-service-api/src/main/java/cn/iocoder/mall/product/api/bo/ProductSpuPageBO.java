package cn.iocoder.mall.product.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品 SPU 分页 BO
 */
@Data
@Accessors(chain = true)
public class ProductSpuPageBO implements Serializable {

    /**
     * Spu 数组
     */
    private List<ProductSpuBO> list;
    /**
     * 总量
     */
    private Integer total;

}
