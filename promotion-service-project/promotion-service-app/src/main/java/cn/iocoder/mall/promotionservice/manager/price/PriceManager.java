package cn.iocoder.mall.promotionservice.manager.price;

import cn.iocoder.common.framework.exception.util.ServiceExceptionUtil;
import cn.iocoder.common.framework.util.CollectionUtils;
import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.productservice.rpc.sku.ProductSkuRpc;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuListQueryReqDTO;
import cn.iocoder.mall.productservice.rpc.sku.dto.ProductSkuRespDTO;
import cn.iocoder.mall.promotion.api.enums.PromotionActivityStatusEnum;
import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.promotionservice.service.activity.PromotionActivityService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.iocoder.mall.promotion.api.enums.PromotionErrorCodeConstants.PRICE_PRODUCT_SKU_NOT_EXISTS;

@Service
@Validated
public class PriceManager {

    @DubboReference(version = "${dubbo.consumer.ProductSkuRpc.version}")
    private ProductSkuRpc productSkuRpc;

    @Autowired
    private PromotionActivityService promotionActivityService;

    public PriceProductCalcRespDTO calcProductPrice(PriceProductCalcReqDTO calcReqDTO) {
        // TODO 芋艿，补充一些表单校验。例如说，需要传入用户编号。
        // 校验商品都存在
        Map<Integer, PriceProductCalcReqDTO.Item> calcProductItemDTOMap = CollectionUtils.convertMap(
                calcReqDTO.getItems(), PriceProductCalcReqDTO.Item::getSkuId);
        CommonResult<List<ProductSkuRespDTO>> listProductSkusResult = productSkuRpc.listProductSkus(
                new ProductSkuListQueryReqDTO().setProductSkuIds(calcProductItemDTOMap.keySet()));
        listProductSkusResult.checkError();
        if (calcReqDTO.getItems().size() != listProductSkusResult.getData().size()) {
            throw ServiceExceptionUtil.exception(PRICE_PRODUCT_SKU_NOT_EXISTS);
        }
        // TODO 库存相关
        // 查询促销活动
        List<PromotionActivityRespDTO> activityRespDTOs = promotionActivityService.listPromotionActivitiesBySpuIds(
                calcProductItemDTOMap.keySet(), Collections.singleton(PromotionActivityStatusEnum.RUN.getValue()));
        // 拼装结果（主要是计算价格）
        PriceProductCalcRespDTO calcRespDTO = new PriceProductCalcRespDTO();
        // 1. 创建初始的每一项的数组
        List<PriceProductCalcRespDTO.Item> calcItemRespDTOs = initCalcOrderPriceItems(
                listProductSkusResult.getData(), calcProductItemDTOMap);
        // 2. 计算【限时折扣】促销
//        modifyPriceByTimeLimitDiscount(items, activityList);
        // 3. 计算【满减送】促销
        // 4. 计算优惠劵
        // 5. 计算最终的价格
        return null;
    }

    private List<PriceProductCalcRespDTO.Item> initCalcOrderPriceItems(List<ProductSkuRespDTO> skus,
                                                                       Map<Integer, PriceProductCalcReqDTO.Item> calcProductItemDTOMap) {
        List<PriceProductCalcRespDTO.Item> items = new ArrayList<>();
        for (ProductSkuRespDTO sku : skus) {
            PriceProductCalcRespDTO.Item item = new PriceProductCalcRespDTO.Item();
            items.add(item);
            // 将是否选中，购物数量，复制到 item 中
            PriceProductCalcReqDTO.Item calcOrderItem = calcProductItemDTOMap.get(sku.getId());
            item.setBuyQuantity(calcOrderItem.getQuantity());
            // 计算初始价格
            item.setOriginPrice(sku.getPrice());
            item.setBuyPrice(sku.getPrice());
            item.setPresentPrice(sku.getPrice());
            item.setBuyTotal(sku.getPrice() * calcOrderItem.getQuantity());
            item.setDiscountTotal(0);
            item.setPresentTotal(item.getBuyTotal());
        }
        return items;
    }

//    private void modifyPriceByTimeLimitDiscount(List<CalcOrderPriceBO.Item> items, List<PromotionActivityBO> activityList) {
//        for (CalcOrderPriceBO.Item item : items) {
//            // 获得符合条件的限时折扣
//            PromotionActivityBO timeLimitedDiscount = activityList.stream()
//                    .filter(activity -> PromotionActivityTypeEnum.TIME_LIMITED_DISCOUNT.getValue().equals(activity.getActivityType())
//                            && activity.getTimeLimitedDiscount().getItems().stream().anyMatch(item0 -> item0.getSpuId().equals(item.getSpu().getId())))
//                    .findFirst().orElse(null);
//            if (timeLimitedDiscount == null) {
//                continue;
//            }
//            // 计算价格
//            ProductSkuBO sku = new ProductSkuBO().setId(item.getId()).setSpuId(item.getSpu().getId()).setPrice(item.getPrice());
//            Integer newPrice = calcSkuPriceByTimeLimitDiscount(sku, timeLimitedDiscount);
//            if (newPrice.equals(item.getPrice())) {
//                continue;
//            }
//            // 设置优惠
//            item.setActivity(timeLimitedDiscount);
//            // 设置价格
//            item.setBuyPrice(newPrice);
//            item.setBuyTotal(newPrice * item.getBuyQuantity());
//            item.setPresentTotal(item.getBuyTotal() - item.getDiscountTotal());
//            item.setPresentPrice(item.getPresentTotal() / item.getBuyQuantity());
//        }
//    }

}
