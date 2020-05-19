package cn.iocoder.mall.order.rest.convert.comment;

import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import cn.iocoder.mall.order.rest.request.comment.UsersOrderCommentAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * UsersOrderCommentConvert
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/13 0:15
 */
@Mapper
public interface UsersOrderCommentConvert {

    UsersOrderCommentConvert INSTANCE = Mappers.getMapper(UsersOrderCommentConvert.class);


    /**
     * 保存订单评论参数转换
     *
     * @param request
     * @return
     */
    OrderCommentAddDTO convert(UsersOrderCommentAddRequest request);

}
