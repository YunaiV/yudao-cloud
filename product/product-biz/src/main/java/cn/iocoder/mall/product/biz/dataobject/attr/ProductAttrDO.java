package cn.iocoder.mall.product.biz.dataobject.attr;

import cn.iocoder.mall.mybatis.core.dataobject.DeletableDO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Product 规格
 */
@Data
@Accessors(chain = true)
public class ProductAttrDO extends DeletableDO {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态
     *
     * 1-开启
     * 2-禁用
     */
    private Integer status;

}
