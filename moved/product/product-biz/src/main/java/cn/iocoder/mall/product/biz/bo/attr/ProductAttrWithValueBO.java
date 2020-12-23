package cn.iocoder.mall.product.biz.bo.attr;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductAttrWithValueBO extends ProductAttrBO {

    /**
     * 规格值数组
     */
    private List<ProductAttrValueBO> values;
}
