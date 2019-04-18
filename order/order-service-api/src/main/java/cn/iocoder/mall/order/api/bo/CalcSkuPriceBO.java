package cn.iocoder.mall.order.api.bo;

import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 计算商品 SKU 价格结果 BO
 */
@Data
@Accessors(chain = true)
public class CalcSkuPriceBO {

    /**
     * 满减送促销活动
     */
    private PromotionActivityBO fullPrivilege;
    /**
     * 电视和折扣促销活动
     */
    private PromotionActivityBO timeLimitedDiscount;
    /**
     * 原价格，单位：分。
     */
    private Integer originalPrice;
    /**
     * 购买价格，单位：分。
     */
    private Integer buyPrice;

}
