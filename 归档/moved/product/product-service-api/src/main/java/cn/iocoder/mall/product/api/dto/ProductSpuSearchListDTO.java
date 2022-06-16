package cn.iocoder.mall.product.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 商品 Spu 搜索列表 DTO
 */
@Data
@Accessors(chain = true)
public class ProductSpuSearchListDTO {

    /**
     * 商品名
     *
     * 模糊匹配
     */
    private String name;

}
