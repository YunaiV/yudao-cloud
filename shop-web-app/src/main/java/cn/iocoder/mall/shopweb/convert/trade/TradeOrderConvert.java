package cn.iocoder.mall.shopweb.convert.trade;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.promotion.api.rpc.coupon.dto.card.CouponCardAvailableListReqDTO;
import cn.iocoder.mall.promotion.api.rpc.price.dto.PriceProductCalcRespDTO;
import cn.iocoder.mall.shopweb.controller.trade.vo.order.TradeOrderConfirmCreateInfoRespVO;
import cn.iocoder.mall.shopweb.controller.trade.vo.order.TradeOrderCreateReqVO;
import cn.iocoder.mall.shopweb.controller.trade.vo.order.TradeOrderPageReqVO;
import cn.iocoder.mall.shopweb.controller.trade.vo.order.TradeOrderRespVO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderCreateReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface TradeOrderConvert {

    TradeOrderConvert INSTANCE = Mappers.getMapper(TradeOrderConvert.class);

    TradeOrderConfirmCreateInfoRespVO.Fee convert(PriceProductCalcRespDTO.Fee bean);

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

    TradeOrderCreateReqDTO convert(TradeOrderCreateReqVO bean);

    TradeOrderPageReqDTO convert(TradeOrderPageReqVO bean);

    PageResult<TradeOrderRespVO> convertPage(PageResult<TradeOrderRespDTO> page);

    TradeOrderRespVO convert(TradeOrderRespDTO bean);

}
