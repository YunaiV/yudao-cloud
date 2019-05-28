package cn.iocoder.mall.order.api;

import cn.iocoder.mall.order.api.bo.OrderCommentCreateBO;
import cn.iocoder.mall.order.api.bo.OrderCommentInfoAndMerchantReplyBO;
import cn.iocoder.mall.order.api.bo.OrderCommentPageBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentPageDTO;

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
    OrderCommentCreateBO createOrderComment(OrderCommentCreateDTO orderCommentCreateDTO);



    /**
     * 获取评论列表的分页
     * @param orderCommentPageDTO
     * @return
     */
    OrderCommentPageBO getOrderCommentPage(OrderCommentPageDTO orderCommentPageDTO);


    /**
     * 获取评论详情和商家回复
     * @param commentId
     * @return
     */
    OrderCommentInfoAndMerchantReplyBO getOrderCommentInfo(Integer commentId, Integer userType);



    /**
     * 订单评价超时自动好评
     * 采用任务的形式执行
     * @return
     */
    Boolean OrderCommentTimeOutProductCommentTask();



}
