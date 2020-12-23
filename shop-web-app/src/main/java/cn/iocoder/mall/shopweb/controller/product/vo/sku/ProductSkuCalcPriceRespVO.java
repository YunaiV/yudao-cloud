package cn.iocoder.mall.shopweb.controller.product.vo.sku;

import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel("计算商品 SKU 价格结果 VO")
@Data
@Accessors(chain = true)
// TODO 芋艿：swagger 注解
public class ProductSkuCalcPriceRespVO {

    /**
     * 原价格，单位：分。
     */
    private Integer originalPrice;
    /**
     * 最终价格，单位：分。
     */
    private Integer buyPrice;
    /**
     * 满减送促销活动
     *
     * TODO 芋艿，后续改成 VO
     */
    private PromotionActivityRespDTO fullPrivilege;
    /**
     * 限时折扣促销活动
     *
     * TODO 芋艿，后续改成 VO
     */
    private PromotionActivityRespDTO timeLimitedDiscount;

}
