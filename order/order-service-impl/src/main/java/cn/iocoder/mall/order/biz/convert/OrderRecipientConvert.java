package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderRecipientBO;
import cn.iocoder.mall.order.api.dto.OrderCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单收件人信息
 *
 * @author Sin
 * @time 2019-03-31 12:50
 */
@Mapper
public interface OrderRecipientConvert {

    OrderRecipientConvert INSTANCE = Mappers.getMapper(OrderRecipientConvert.class);

    @Mappings({})
    OrderRecipientDO convert(OrderCreateDTO orderCreateDTO);

    @Mappings({})
    List<OrderRecipientBO> convert(List<OrderRecipientDO> orderRecipientDOList);
}
