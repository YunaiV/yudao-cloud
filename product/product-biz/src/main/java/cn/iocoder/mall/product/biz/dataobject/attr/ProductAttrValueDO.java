package cn.iocoder.mall.product.biz.dataobject.attr;

import cn.iocoder.mall.mybatis.dataobject.DeletableDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Product 规格值
 */
@Data
@EqualsAndHashCode(callSuper = true)
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
     * <p>
     * 1-正常
     * 2-禁用
     */
    private Integer status;

}
