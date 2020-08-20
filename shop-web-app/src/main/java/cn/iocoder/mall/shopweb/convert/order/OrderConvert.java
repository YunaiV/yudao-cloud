package cn.iocoder.mall.shopweb.convert.order;

import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.order.vo.order.OrderConfirmCreateInfoRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderConfirmCreateInfoRespVO.Fee convert(PriceProductCalcRespDTO.Fee bean);

    default List<CouponCardAvailableListReqDTO.Item> convertList(List<PriceProductCalcRespDTO.ItemGroup> itemGroups) {
        List<CouponCardAvailableListReqDTO.Item> items = new ArrayList<>();
        itemGroups.forEach(itemGroup -> items.addAll(itemGroup.getItems().stream().map(
                item -> new CouponCardAvailableListReqDTO.Item()
                        .setSpuId(item.getSpuId())
                        .setSkuId(item.getSkuId())
                        .setCid(item.getCid())
                        .setPrice(item.getBuyPrice())
                        .setQuantity(item.getBuyQuantity()))
                .collect(Collectors.toList())));
        return items;
    }

}
