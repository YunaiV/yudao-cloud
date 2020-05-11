package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单收件人信息
 *
 * @author Sin
 * @time 2019-03-31 12:50
 */
@Mapper
public interface OrderRecipientConvert {

    OrderRecipientConvert INSTANCE = Mappers.getMapper(OrderRecipientConvert.class);

//    @Mappings({})
//    OrderRecipientDO convert(OrderCreateDTO orderCreateDTO);
//
//    @Mappings({})
//    OrderRecipientDO convert(UserAddressBO userAddressBO);
//
//    @Mappings({})
//    OrderRecipientBO convert(OrderRecipientDO orderRecipientDO);
//
//    @Mappings({})
//    List<OrderRecipientBO> convert(List<OrderRecipientDO> orderRecipientDOList);
//
//    @Mappings({})
//    OrderInfoBO.Recipient convertOrderInfoRecipient(OrderRecipientDO orderRecipientDO);
}
