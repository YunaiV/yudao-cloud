package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderCommentBO;
import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
}
