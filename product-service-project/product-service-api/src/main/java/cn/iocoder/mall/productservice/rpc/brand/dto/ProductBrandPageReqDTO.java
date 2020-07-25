package cn.iocoder.mall.productservice.rpc.brand.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 商品品牌分页 Request DTO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductBrandPageReqDTO extends PageParam {

    /**
    * 品牌名称
    */
    private String name;
    /**
    * 状态
    */
    private Integer status;

}
