package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderInfoBO;
import cn.iocoder.mall.order.api.bo.OrderLogisticsInfoWithOrderBO;
import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDetailDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单物流 convert
 *
 * @author Sin
 * @time 2019-03-23 14:39
 */
@Mapper
public interface OrderLogisticsDetailConvert {

    OrderLogisticsDetailConvert INSTANCE = Mappers.getMapper(OrderLogisticsDetailConvert.class);

    @Mappings({})
    OrderInfoBO.LogisticsDetail convertLogisticsDetail(OrderLogisticsDetailDO orderLogisticsDetailDO);
}
