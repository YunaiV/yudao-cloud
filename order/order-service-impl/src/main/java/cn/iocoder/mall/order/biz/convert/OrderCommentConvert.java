package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentInfoBO;
import cn.iocoder.mall.order.api.bo.OrderCommentStateInfoPageBO;
import cn.iocoder.mall.order.api.bo.OrderCommentTimeOutBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *
 * 订单评论 convert
 *
 * @author wtz
 * @time 2019-05-31 18:30
 */
@Mapper
public interface OrderCommentConvert {

    OrderCommentConvert INSTANCE = Mappers.getMapper(OrderCommentConvert.class);

    @Mappings({})
    OrderCommentStateInfoPageBO.OrderCommentStateInfoItem convertOrderCommentStateInfoItem(OrderCommentDO orderCommentDO);

    @Mappings({})
    List<OrderCommentStateInfoPageBO.OrderCommentStateInfoItem> convertOrderCommentStateInfoItems(List<OrderCommentDO> orderCommentDOList);

    @Mappings({})
    OrderCommentDO convertOrderCommentDO(OrderCommentCreateDTO orderCommentCreateDTO);

    @Mappings({})
    OrderCommentCreateBO convertOrderCommentCreateBO(OrderCommentDO orderCommentDO);

    @Mappings({})
    OrderCommentInfoBO convertOrderCommentInfoBO(OrderCommentDO orderCommentDO);

    @Mappings({})
    OrderCommentTimeOutBO convertOrderCommentTimeOutBO(OrderCommentDO orderCommentDO);

    @Mappings({})
    List<OrderCommentTimeOutBO> convertOrderCommentTimeOutBOList(List<OrderCommentDO> orderCommentDOList);



}
