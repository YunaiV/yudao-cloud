package cn.iocoder.mall.promotion.api.rpc.coupon.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 优惠劵分页 DTO
 */
@Data
@Accessors(chain = true)
public class CouponCardPageReqDTO implements Serializable {

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 状态
     */
    private Integer status;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
