package cn.iocoder.mall.productservice.rpc.brand.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
* 商品品牌更新 Request DTO
*/
@Data
@Accessors(chain = true)
public class ProductBrandUpdateReqDTO implements Serializable {

    /**
     * 品牌编号
     */
    @NotNull(message = "品牌编号不能为空")
    private Integer id;
    /**
     * 品牌名称
     */
    @NotEmpty(message = "品牌名称不能为空")
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
    @NotNull(message = "状态不能为空")
    private Integer status;

}
