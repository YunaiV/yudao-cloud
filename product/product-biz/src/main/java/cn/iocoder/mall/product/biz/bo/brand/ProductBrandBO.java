package cn.iocoder.mall.product.biz.bo.brand;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商品品牌 VO
 */
@Data
@Accessors(chain = true)
public class ProductBrandBO implements Serializable {

    /**
     * 规格编号
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片地址
     */
    private String picUrl;

    /**
     * 状态
     *
     * 1-开启
     * 2-禁用
     */
    private Integer status;

}
