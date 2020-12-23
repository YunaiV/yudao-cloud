package cn.iocoder.mall.productservice.service.attr.bo;


import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 商品规格值更新 BO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueUpdateBO {

    /**
     * 规格值编号
     */
    @NotNull(message = "规格值编号不能为空")
    private Integer id;
    /**
     * 规格键编号
     */
    @NotNull(message = "规格键编号不能为空")
    private Integer attrKeyId;
    /**
     * 规格值名字
     */
    @NotEmpty(message = "规格值名字不能为空")
    private String name;
    /**
     * 状态
     */
    @NotNull(message = "状态")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
