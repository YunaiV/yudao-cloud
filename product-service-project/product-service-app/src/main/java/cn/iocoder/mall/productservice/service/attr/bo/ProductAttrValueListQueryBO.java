package cn.iocoder.mall.productservice.service.attr.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 商品规格值的列表查询条件 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueListQueryBO {

    /**
     * 商品规格值编号列表
     */
    private List<Integer> productAttrValueIds;

    /**
     * 商品规格键编号
     */
    private Integer productAttrKeyId;

}
