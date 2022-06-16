package cn.iocoder.mall.productservice.service.attr.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 商品规格值 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueBO {

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
