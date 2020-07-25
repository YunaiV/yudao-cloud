package cn.iocoder.mall.productservice.service.brand.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
* 商品品牌 BO
*/
@Data
@Accessors(chain = true)
public class ProductBrandBO {

    /**
     * 品牌编号（主键）
     */
    private Integer id;
    /**
     * 品牌名称
     */
    private String name;
    /**
     * 品牌描述
     */
    private String description;
    /**
     * 品牌名图片
     */
    private String picUrl;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
