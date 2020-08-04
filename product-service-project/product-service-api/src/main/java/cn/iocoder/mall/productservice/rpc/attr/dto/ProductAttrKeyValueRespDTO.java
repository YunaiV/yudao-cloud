package cn.iocoder.mall.productservice.rpc.attr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品规格 KEY + VALUE 对的 Response DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyValueRespDTO implements Serializable {

    /**
     * 规格 KEY 编号
     */
    private Integer attrKeyId;
    /**
     * 规格 KEY 名
     */
    private String attrKeyName;
    /**
     * 规格 VALUE 编号
     */
    private Integer attrValueId;
    /**
     * 规格 VALUE 名
     */
    private String attrValueName;

}
