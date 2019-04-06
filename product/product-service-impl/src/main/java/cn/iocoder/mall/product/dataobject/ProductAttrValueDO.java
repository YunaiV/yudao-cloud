package cn.iocoder.mall.product.dataobject;

import cn.iocoder.common.framework.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Product 规格值
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueDO extends DeletableDO {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格编号
     */
    private Integer attrId;
    /**
     * 规格值
     */
    private String name;
    /**
     * 状态
     *
     * 1-正常
     * 2-禁用
     */
    private Integer status;

}
