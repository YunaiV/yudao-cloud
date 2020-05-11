package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

//    @Mappings({})
//    OrderCommentStateInfoPageBO.OrderCommentStateInfoItem convertOrderCommentStateInfoItem(
//        OrderCommentDO orderCommentDO);
//
//    @Mappings({})
//    List<OrderCommentStateInfoPageBO.OrderCommentStateInfoItem> convertOrderCommentStateInfoItems(
//        List<OrderCommentDO> orderCommentDOList);
//
//    @Mappings({})
//    OrderCommentDO convertOrderCommentDO(OrderCommentCreateDTO orderCommentCreateDTO);
//
//    @Mappings({})
//    OrderCommentCreateBO convertOrderCommentCreateBO(OrderCommentDO orderCommentDO);
//
//    @Mappings({})
//    OrderCommentInfoBO convertOrderCommentInfoBO(OrderCommentDO orderCommentDO);
//
//    @Mappings({})
//    OrderCommentTimeOutBO convertOrderCommentTimeOutBO(OrderCommentDO orderCommentDO);
//
//    @Mappings({})
//    List<OrderCommentTimeOutBO> convertOrderCommentTimeOutBOList(
//        List<OrderCommentDO> orderCommentDOList);



}
