package cn.iocoder.mall.order.api;

import cn.iocoder.mall.order.api.bo.OrderCommentMerchantReplyBO;
import cn.iocoder.mall.order.api.bo.OrderCommentReplyCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentReplyPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyPageDTO;

import java.util.List;

/**
 *
 * 订单评论回复模块
 *
 * @author wtz
 * @time 2019-05-29 14:30
 *
 */
public interface OrderCommentReplyService {

    /**
     * 分页获取评论回复
     * @param orderCommentReplyPageDTO
     * @return
     */
    OrderCommentReplyPageBO getOrderCommentReplyPage(OrderCommentReplyPageDTO orderCommentReplyPageDTO);


    /**
     * 评论回复创建
     * @param orderCommentReplyCreateDTO
     * @return
     */
    OrderCommentReplyCreateBO createOrderCommentReply(OrderCommentReplyCreateDTO orderCommentReplyCreateDTO);


    /**
     * 获取商家评论回复
     * @param commentId
     * @return
     */
    List<OrderCommentMerchantReplyBO> getOrderCommentMerchantReply(Integer commentId);



}
