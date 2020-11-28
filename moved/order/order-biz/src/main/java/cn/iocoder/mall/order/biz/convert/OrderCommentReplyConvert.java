package cn.iocoder.mall.order.biz.convert;

import org.mapstruct.Mapper;
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

//    @Mappings({})
//    OrderCommentReplyDO convert(OrderCommentReplyCreateDTO orderCommentReplyCreateDTO);
//
//    @Mappings({})
//    OrderCommentReplyCreateBO convert(OrderCommentReplyDO orderCommentReplyDO);
//
//    @Mappings({})
//    List<OrderCommentMerchantReplyBO> convert(List<OrderCommentReplyDO> orderCommentReplyDOList);
//
//    @Mappings({})
//    List<OrderCommentReplyPageBO.OrderCommentReplayItem> convertOrderCommentReplayItem(
//        List<OrderCommentReplyDO> orderCommentReplyDOList);
}
