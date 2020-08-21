package cn.iocoder.mall.promotion.api.rpc.coupon.dto.template;

import cn.iocoder.common.framework.validator.InEnum;
import cn.iocoder.mall.promotion.api.enums.coupon.template.CouponTemplateStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 优惠劵（码）模板更新状态 Request DTO
 */
@Data
@Accessors(chain = true)
public class CouponCardTemplateUpdateStatusReqDTO implements Serializable {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空")
    private Integer id;
    /**
     * 状态
     */
    @NotNull(message = "状态不能为空")
    @InEnum(value = CouponTemplateStatusEnum.class, message = "状态必须是 {value}")
    private Integer status;

}
