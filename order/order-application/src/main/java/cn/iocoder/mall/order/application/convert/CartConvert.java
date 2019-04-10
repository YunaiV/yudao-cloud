package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.bo.CalcOrderPriceBO;
import cn.iocoder.mall.order.application.vo.UsersOrderConfirmCreateVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartConvert {

    CartConvert INSTANCE = Mappers.getMapper(CartConvert.class);

    UsersOrderConfirmCreateVO convert(CalcOrderPriceBO calcOrderPriceBO);

}
