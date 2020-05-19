package cn.iocoder.mall.order.biz.service.comment;

import cn.iocoder.mall.order.biz.bo.comment.OrderCommentBO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;

/**
 * 订单评论业务
 *
 * @author xiaofeng
 * @version 1.0
 * @date 2020/05/17 15:24
 */
@Validated
public interface OrderCommentService {

    /**
     * 添加订单评论
     *
     * @param orderCommentAddDTO
     * @return
     */
    Boolean addOrderComment(@Valid OrderCommentAddDTO orderCommentAddDTO);

}
