package cn.iocoder.mall.search.biz.convert;

import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.product.api.bo.ProductSpuDetailBO;
import cn.iocoder.mall.promotion.api.bo.PromotionActivityBO;
import cn.iocoder.mall.search.biz.bo.ProductBO;
import cn.iocoder.mall.search.biz.dataobject.ESProductDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductSearchConvert {

    ProductSearchConvert INSTANCE = Mappers.getMapper(ProductSearchConvert.class);

    @Mappings({})
    ESProductDO convert(ProductSpuDetailBO spu);

    @Mappings({})
    default ESProductDO convert(ProductSpuDetailBO spu, CalcSkuPriceBO calcSkuPrice) {
        // Spu 的基础数据
        ESProductDO product = this.convert(spu);
        product.setOriginalPrice(calcSkuPrice.getOriginalPrice()).setBuyPrice(calcSkuPrice.getBuyPrice());
        // 设置促销活动相关字段
        if (calcSkuPrice.getTimeLimitedDiscount() != null) {
            PromotionActivityBO activity = calcSkuPrice.getTimeLimitedDiscount();
            product.setPromotionActivityId(activity.getId()).setPromotionActivityTitle(activity.getTitle())
                    .setPromotionActivityType(activity.getActivityType());
        }
        // 返回
        return product;
    }

    List<ProductBO> convert(List<ESProductDO> list);

}
