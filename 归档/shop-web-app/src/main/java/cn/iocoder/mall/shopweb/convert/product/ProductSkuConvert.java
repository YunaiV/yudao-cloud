package cn.iocoder.mall.shopweb.convert.product;

import cn.iocoder.mall.promotion.api.rpc.activity.dto.PromotionActivityRespDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.product.vo.sku.ProductSkuCalcPriceRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductSkuConvert {

    ProductSkuConvert INSTANCE = Mappers.getMapper(ProductSkuConvert.class);

    default ProductSkuCalcPriceRespVO convert(PriceProductCalcRespDTO.Item item,
                                              PromotionActivityRespDTO fullPrivilege, PromotionActivityRespDTO timeLimitedDiscount) {
        return new ProductSkuCalcPriceRespVO().setOriginalPrice(item.getOriginPrice()).setBuyPrice(item.getBuyPrice())
                .setFullPrivilege(fullPrivilege).setTimeLimitedDiscount(timeLimitedDiscount);
    }

}
