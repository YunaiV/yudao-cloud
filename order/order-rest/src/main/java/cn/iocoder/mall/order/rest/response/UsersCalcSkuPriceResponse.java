package cn.iocoder.mall.order.rest.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("计算商品 SKU 价格结果 VO")
@Data
@Accessors(chain = true)
public class UsersCalcSkuPriceResponse {

    /**
     * 满减送促销活动
     *
     * TODO 芋艿，后续改成 VO
     */
//    private PromotionActivityBO fullPrivilege;
    /**
     * 电视和折扣促销活动
     *
     * TODO 芋艿，后续改成 VO
     */
//    private PromotionActivityBO timeLimitedDiscount;
    /**
     * 原价格，单位：分。
     */
    private Integer originalPrice;
    /**
     * 最终价格，单位：分。
     */
    private Integer buyPrice;

}
