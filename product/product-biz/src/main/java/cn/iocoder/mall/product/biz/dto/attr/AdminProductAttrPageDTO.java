package cn.iocoder.mall.product.biz.dto.attr;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品规格模块 - 商品规格分页 DTO
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AdminProductAttrPageDTO extends PageParam {
    /**
     * 商品规格名字
     */
    private String name;
}
