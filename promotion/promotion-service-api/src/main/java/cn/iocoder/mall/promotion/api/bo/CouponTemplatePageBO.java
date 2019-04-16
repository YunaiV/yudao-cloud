package cn.iocoder.mall.promotion.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 优惠劵（码）模板分页 BO
 */
@Data
@Accessors(chain = true)
public class CouponTemplatePageBO implements Serializable {

    /**
     * 优惠劵（码）数组
     */
    private List<CouponTemplateBO> list;
    /**
     * 总量
     */
    private Integer total;

}
