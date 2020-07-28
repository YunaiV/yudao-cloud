package cn.iocoder.mall.productservice.rpc.attr.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 商品规格键创建 Request DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrKeyCreateReqDTO implements Serializable {

    /**
     * 规格键名称
     */
    @NotEmpty(message = "规格键名称不能为空")
    private String name;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
