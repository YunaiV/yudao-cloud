package cn.iocoder.mall.productservice.rpc.attr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品规格值 Response DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueRespDTO implements Serializable {

    /**
     * 规格值编号
     */
    private Integer id;
    /**
     * 规格键编号
     */
    private Integer attrKeyId;
    /**
     * 规格值名字
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
