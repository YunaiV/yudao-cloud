package cn.iocoder.mall.order.biz.convert.comment;

import cn.iocoder.mall.order.biz.dataobject.comment.OrderCommentDO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 订单评论转换
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/19 23:06
 */
@Mapper
public interface OrderCommentConvert {

    OrderCommentConvert INSTANCE = Mappers.getMapper(OrderCommentConvert.class);

    /**
     * 参数转成 DO
     *
     * @param orderCommentAddDTO
     * @return
     */
    OrderCommentDO convert(OrderCommentAddDTO orderCommentAddDTO);

}
