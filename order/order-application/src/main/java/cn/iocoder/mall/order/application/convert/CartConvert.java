package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.api.bo.CalcSkuPriceBO;
import cn.iocoder.mall.order.application.vo.UsersCalcSkuPriceVO;
import cn.iocoder.mall.order.application.vo.UsersCartDetailVO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import cn.iocoder.mall.promotion.api.dto.CouponCardSpuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    UsersOrderConfirmCreateVO convert(CalcOrderPriceBO calcOrderPriceBO);

    UsersCartDetailVO convert2(CalcOrderPriceBO calcOrderPriceBO);

    UsersCalcSkuPriceVO convert2(CalcSkuPriceBO calcSkuPriceBO);

    default List<CouponCardSpuDTO> convertList(List<CalcOrderPriceBO.ItemGroup> itemGroups) {
        List<CouponCardSpuDTO> items = new ArrayList<>();
        itemGroups.forEach(itemGroup -> items.addAll(itemGroup.getItems().stream().map(
                item -> new CouponCardSpuDTO()
                        .setSpuId(item.getSpu().getId())
                        .setSkuId(item.getId())
                        .setCategoryId(item.getSpu().getCid())
                        .setPrice(item.getBuyPrice())
                        .setQuantity(item.getBuyQuantity()))
                .collect(Collectors.toList())));
        return items;
    }

}
