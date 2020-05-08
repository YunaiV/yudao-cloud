package cn.iocoder.mall.order.rest.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

//    UsersOrderConfirmCreateVO convert(CalcOrderPriceBO calcOrderPriceBO);
//
//    UsersCartDetailVO convert2(CalcOrderPriceBO calcOrderPriceBO);
//
//    UsersCalcSkuPriceVO convert2(CalcSkuPriceBO calcSkuPriceBO);
//
//    default List<CouponCardSpuDTO> convertList(List<CalcOrderPriceBO.ItemGroup> itemGroups) {
//        List<CouponCardSpuDTO> items = new ArrayList<>();
//        itemGroups.forEach(itemGroup -> items.addAll(itemGroup.getItems().stream().map(
//                item -> new CouponCardSpuDTO()
//                        .setSpuId(item.getSpu().getId())
//                        .setSkuId(item.getId())
//                        .setCategoryId(item.getSpu().getCid())
//                        .setPrice(item.getBuyPrice())
//                        .setQuantity(item.getBuyQuantity()))
//                .collect(Collectors.toList())));
//        return items;
//    }

}
