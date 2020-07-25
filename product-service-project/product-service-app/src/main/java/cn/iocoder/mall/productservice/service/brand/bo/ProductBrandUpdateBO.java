package cn.iocoder.mall.productservice.service.brand.bo;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
* 商品品牌更新 BO
*/
@Data
@Accessors(chain = true)
public class ProductBrandUpdateBO {

    /**
     * 品牌编号（主键）
     */
    @NotNull(message = "品牌编号（主键）不能为空")
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
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
