package cn.iocoder.mall.productservice.rpc.attr.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 商品规格键分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductAttrKeyPageReqDTO extends PageParam {

    /**
    * 规格键名称
    */
    private String name;
    /**
    * 状态
    */
    private Integer status;

}
