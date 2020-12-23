package cn.iocoder.mall.shopweb.service.product;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.promotion.api.rpc.activity.PromotionActivityRpc;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.PriceRpc;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.sku.ProductSkuCalcPriceRespVO;
import cn.iocoder.mall.shopweb.convert.product.ProductSkuConvert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;

/**
 * 商品 SKU Manager
 */
@Service
@Validated
public class ProductSkuManager {

    @DubboReference(version = "${dubbo.consumer.PriceRpc.version}")
    private PriceRpc priceRpc;
    @DubboReference(version = "${dubbo.consumer.PromotionActivityRpc.version}")
    private PromotionActivityRpc promotionActivityRpc;

    /**
     * 计算商品 SKU 价格
     *
     * @param userId 用户编号
     * @param skuId 商品 SKU 编号
     * @return SKU 价格明细
     */
    public ProductSkuCalcPriceRespVO calcProductSkuPrice(Integer userId, Integer skuId) {
        CommonResult<PriceProductCalcRespDTO> calcProductPriceResult = priceRpc.calcProductPrice(new PriceProductCalcReqDTO().setUserId(userId)
                .setItems(Collections.singletonList(new PriceProductCalcReqDTO.Item(skuId, 1, true))));
        calcProductPriceResult.checkError();
        // 拼接结果
        PriceProductCalcRespDTO.ItemGroup itemGroup = calcProductPriceResult.getData().getItemGroups().get(0);
        // 1. 加载 满减送 促销活动
        PromotionActivityRespDTO fullPrivilege = itemGroup.getActivityId() != null ? this.getPromotionActivity(itemGroup.getActivityId()) : null;
        // 2. 加载 限时折扣 促销活动
        PriceProductCalcRespDTO.Item item = itemGroup.getItems().get(0);
        PromotionActivityRespDTO timeLimitedDiscount = item.getActivityId() != null ? this.getPromotionActivity(item.getActivityId()) : null;
        // 3. 最终组装
        return ProductSkuConvert.INSTANCE.convert(item, fullPrivilege, timeLimitedDiscount);
    }

    private PromotionActivityRespDTO getPromotionActivity(Integer activityId) {
        CommonResult<List<PromotionActivityRespDTO>> listPromotionActivitiesResult = promotionActivityRpc.listPromotionActivities(
                 new PromotionActivityListReqDTO().setActiveIds(Collections.singleton(activityId)));
        listPromotionActivitiesResult.checkError();
        return listPromotionActivitiesResult.getData().get(0);
    }

}
