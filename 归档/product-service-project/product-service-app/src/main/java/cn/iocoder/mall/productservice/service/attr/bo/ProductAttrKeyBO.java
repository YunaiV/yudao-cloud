package cn.iocoder.mall.productservice.service.attr.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商品规格键 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyBO {

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
