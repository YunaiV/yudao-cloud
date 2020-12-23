package cn.iocoder.mall.order.biz.service.comment;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentInfoBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentPageBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentStateInfoPageBO;
import cn.iocoder.mall.order.biz.bo.comment.OrderCommentTimeOutBO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentAddDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentPageDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentStateInfoPageDTO;
import cn.iocoder.mall.order.biz.dto.comment.OrderCommentTimeOutPageDTO;
import java.util.List;
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

    /**
     * 分页
     *
     * @param orderCommentPageDTO
     * @return
     */
    PageResult<OrderCommentPageBO> page(OrderCommentPageDTO orderCommentPageDTO);

    /**
     * 获取订单评论信息
     *
     * @param commentId
     * @return
     */
    OrderCommentInfoBO getOrderCommentInfo(Integer commentId);


    /**
     * 获取订单评论状态详情
     *
     * @param orderCommentStateInfoPageDTO
     * @return
     */
    OrderCommentStateInfoPageBO getOrderCommentStateInfoPage(
            OrderCommentStateInfoPageDTO orderCommentStateInfoPageDTO);

    /**
     * 获取订单评论超时分页
     *
     * @param orderCommentTimeOutPageDTO
     * @return
     */
    List<OrderCommentTimeOutBO> getOrderCommentTimeOutPage(
            OrderCommentTimeOutPageDTO orderCommentTimeOutPageDTO);


    /**
     * 批量更新订单评论状态
     *
     * @param orderCommentTimeOutBOList
     */
    void updateBatchOrderCommentState(List<OrderCommentTimeOutBO> orderCommentTimeOutBOList);


}
