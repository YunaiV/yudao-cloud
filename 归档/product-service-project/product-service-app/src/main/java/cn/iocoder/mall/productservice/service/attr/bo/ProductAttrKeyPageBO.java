package cn.iocoder.mall.productservice.service.attr.bo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品规格键分页 BO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductAttrKeyPageBO extends PageParam {

    /**
     * 规格键名称，模糊匹配
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;

}
