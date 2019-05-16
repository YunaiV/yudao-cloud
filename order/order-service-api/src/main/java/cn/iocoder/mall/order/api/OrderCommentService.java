package cn.iocoder.mall.order.api;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentInfoBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO;
import cn.iocoder.mall.order.api.bo.OrderCommentReplyCreateBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyCreateDTO;

/**
 * 订单评论模块
 *
 * @author wtz
 * @time 2019-05-14 22:10
 */
public interface OrderCommentService {

    /**
     * 评论创建
     * @param orderCommentCreateDTO
     * @return
     */
    CommonResult<OrderCommentCreateBO> createOrderComment(OrderCommentCreateDTO orderCommentCreateDTO);


    /**
     * 评论回复创建
     * @param orderCommentReplyCreateDTO
     * @return
     */
    CommonResult<OrderCommentReplyCreateBO> createOrderCommentReply(OrderCommentReplyCreateDTO orderCommentReplyCreateDTO);

    /**
     * 获取评论列表的分页
     * @param productSpuId
     * @return
     */
    CommonResult<OrderCommentPageBO> getOrderCommentPage(Integer productSpuId);


    /**
     * 获取评论详情
     * @param commentId
     * @return
     */
    CommonResult<OrderCommentInfoBO> getOrderCommentInfo(Integer commentId);



}
