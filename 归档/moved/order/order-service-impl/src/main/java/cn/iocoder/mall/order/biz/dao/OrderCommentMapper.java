package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.api.bo.OrderCommentTimeOutBO;
import cn.iocoder.mall.order.api.dto.OrderCommentPageDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentStateInfoPageDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentTimeOutPageDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import cn.iocoder.mall.order.biz.dataobject.OrderItemDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 *
 * 订单评论 mapper
 *
 * @author wtz
 * @time 2019-05-16 20:52
 */
@Repository
public interface OrderCommentMapper{


    /**
     * 插入订单评论
     * @param orderCommentDO
     * @return
     */
    void insert(OrderCommentDO orderCommentDO);


    /**
     * 根据 sku id 查询评论总条数
     * @param productSkuId
     * @return
     */
    int selectCommentTotalCountByProductSkuId(@Param("productSkuId") Integer productSkuId);


    /**
     * 分页获取评论
     * @param orderCommentPageDTO
     * @return
     */
    List<OrderCommentDO> selectCommentPage(OrderCommentPageDTO orderCommentPageDTO);


    /**
     * 根据评论 id 查询评论详情
     * @param id
     * @return
     */
    OrderCommentDO selectCommentInfoByCommentId(@Param("id") Integer id);


    /**
     * 订单评论状态信息详情
     * @param orderCommentStateInfoPageDTO
     * @return
     */
    List<OrderCommentDO> selectOrderCommentStateInfoPage(OrderCommentStateInfoPageDTO orderCommentStateInfoPageDTO);


    /**
     * 订单评论状态总数
     * @param userId,commentState
     * @return
     */
    int selectOrderCommentStateInfoTotal(@Param("userId") Integer userId,
                                         @Param("commentState") Integer commentState);


    /**
     * 订单评论超时分页
     * @param orderCommentTimeOutPageDTO
     * @return
     */
    List<OrderCommentDO> selectOrderCommentTimeOutPage(@Param("commentTimeOut") OrderCommentTimeOutPageDTO orderCommentTimeOutPageDTO);

    /**
     * 批量更新订单评论状态
     * @param orderCommentTimeOutBOList
     * @param commentState
     */
    void updateBatchOrderCommentState(@Param("commentState") Integer commentState,
            @Param("list") List<OrderCommentTimeOutBO> orderCommentTimeOutBOList);



}
