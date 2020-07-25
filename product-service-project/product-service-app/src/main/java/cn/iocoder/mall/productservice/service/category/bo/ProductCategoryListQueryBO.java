package cn.iocoder.mall.productservice.service.category.bo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品分类列表查询 BO
 */
@Data
@Accessors(chain = true)
public class ProductCategoryListQueryBO {

    /**
     * 父编号
     */
    private Integer pid;
    /**
     * 状态
     */
    @InEnum(value = CommonStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
