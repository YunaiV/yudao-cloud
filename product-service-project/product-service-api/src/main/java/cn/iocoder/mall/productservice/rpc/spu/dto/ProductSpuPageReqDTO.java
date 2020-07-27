package cn.iocoder.mall.productservice.rpc.spu.dto;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* 商品 SPU 分页 Request DTO
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ProductSpuPageReqDTO extends PageParam {

    /**
    * SPU 名字
    */
    private String name;
    /**
     * 分类编号
     */
    private Integer cid;
    /**
     * 是否可见
     */
    private Boolean visible;
    /**
    * 是否有库存
    */
    private Boolean hasQuantity;

}
