package cn.iocoder.mall.promotion.api.rpc.coupon.dto.template;

import cn.iocoder.common.framework.vo.PageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 优惠劵模板分页 Request DTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CouponTemplatePageReqDTO extends PageParam {

    /**
     * 类型
     */
    private Integer type;
    /**
     * 标题
     */
    private String title;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 优惠类型
     */
    private Integer preferentialType;

}
