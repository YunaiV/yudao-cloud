package cn.iocoder.mall.order.biz.convert;

import cn.iocoder.mall.order.api.bo.OrderCommentReplyCreateBO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyCreateDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentReplyDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 *
 * 评论回复 convert
 *
 * @author wtz
 * @time 2019-05-31 18:30
 */
@Mapper
public interface OrderCommentReplyConvert {

    OrderCommentReplyConvert INSTANCE = Mappers.getMapper(OrderCommentReplyConvert.class);

    @Mappings({})
    OrderCommentReplyDO convert(OrderCommentReplyCreateDTO orderCommentReplyCreateDTO);

    @Mappings({})
    OrderCommentReplyCreateBO convert(OrderCommentReplyDO orderCommentReplyDO);
}
