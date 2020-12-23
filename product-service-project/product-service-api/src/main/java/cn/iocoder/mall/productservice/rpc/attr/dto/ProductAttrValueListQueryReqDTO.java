package cn.iocoder.mall.productservice.rpc.attr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格值的列表查询条件 Request DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueListQueryReqDTO implements Serializable {

    /**
     * 商品规格值编号列表
     */
    private List<Integer> productAttrValueIds;

    /**
     * 商品规格键编号
     */
    private Integer productAttrKeyId;

}
