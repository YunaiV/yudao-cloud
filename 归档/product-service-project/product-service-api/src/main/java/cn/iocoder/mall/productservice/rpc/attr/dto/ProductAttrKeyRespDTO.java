package cn.iocoder.mall.productservice.rpc.attr.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品规格键 Response DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyRespDTO implements Serializable {

    /**
     * 规格键编号
     */
    private Integer id;
    /**
     * 规格键名称
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
