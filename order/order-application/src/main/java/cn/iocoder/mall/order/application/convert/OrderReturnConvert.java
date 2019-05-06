package cn.iocoder.mall.order.application.convert;

import cn.iocoder.mall.order.api.dto.OrderReturnApplyDTO;
import cn.iocoder.mall.order.api.dto.OrderReturnQueryDTO;
import cn.iocoder.mall.order.application.po.admin.OrderReturnQueryPO;
import cn.iocoder.mall.order.application.po.user.OrderReturnApplyPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 订单退货
 *
 * @author Sin
 * @time 2019-04-25 21:54
 */
@Mapper
public interface OrderReturnConvert {

    OrderReturnConvert INSTANCE = Mappers.getMapper(OrderReturnConvert.class);

    @Mappings({})
    OrderReturnApplyDTO convert(OrderReturnApplyPO orderReturnApplyPO);

    @Mappings({})
    OrderReturnQueryDTO convert(OrderReturnQueryPO orderReturnQueryPO);
}
