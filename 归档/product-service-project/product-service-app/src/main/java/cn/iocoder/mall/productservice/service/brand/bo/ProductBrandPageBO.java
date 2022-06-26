package cn.iocoder.mall.productservice.service.brand.bo;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 商品品牌分页 BO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductBrandPageBO extends PageParam {

    /**
    * 品牌名称
     *
     * 模糊匹配
    */
    private String name;
    /**
    * 状态
    */
    private Integer status;

}
