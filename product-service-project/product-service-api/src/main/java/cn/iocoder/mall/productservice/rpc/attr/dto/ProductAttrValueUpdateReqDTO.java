package cn.iocoder.mall.productservice.rpc.attr.dto;

import cn.iocoder.common.framework.enums.CommonStatusEnum;
import cn.iocoder.common.framework.validator.InEnum;
import lombok.*;
import lombok.experimental.*;

import io.swagger.annotations.*;

import java.util.*;
import java.io.Serializable;

import javax.validation.constraints.*;

/**
 * 商品规格值更新 Request DTO
 */
@Data
@Accessors(chain = true)
public class ProductAttrValueUpdateReqDTO implements Serializable {

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
    @NotNull(message = "状态不能为空")
    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
