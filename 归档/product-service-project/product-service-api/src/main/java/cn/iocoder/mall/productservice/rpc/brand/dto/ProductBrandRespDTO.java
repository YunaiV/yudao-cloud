package cn.iocoder.mall.productservice.rpc.brand.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* 商品品牌 Response DTO
*/
@Data
@Accessors(chain = true)
public class ProductBrandRespDTO implements Serializable {

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
