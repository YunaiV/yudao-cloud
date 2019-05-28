package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 订单评论 convert
 */
@Mapper
public interface OrderCommentConvert {

    OrderCommentConvert INSTANCE = Mappers.getMapper(OrderCommentConvert.class);

    @Mappings({})
    OrderCommentDO convert(OrderCommentCreateDTO orderCommentCreateDTO);

    @Mappings({})
    OrderCommentCreateBO convert(OrderCommentDO orderCommentDO);

    @Mappings({})
    List<OrderCommentPageBO.OrderCommentItem> convert(List<OrderCommentDO> orderCommentDOList);
}
