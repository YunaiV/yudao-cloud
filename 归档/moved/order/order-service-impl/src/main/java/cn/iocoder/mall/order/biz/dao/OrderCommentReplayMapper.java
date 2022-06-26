package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.api.bo.OrderCommentBO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentReplyPageDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentReplyDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 订单评论回复 mapper
 *
 * @author wtz
 * @time 2019-05-16 21:33
 */
@Repository
public interface OrderCommentReplayMapper {

    /**
     * 插入订单评论回复
     * @param orderCommentReplyDO
     * @return
     */
    void insert(OrderCommentReplyDO orderCommentReplyDO);

    /**
     * 根据评论 id 和用户类型获取商家回复
     * @param commentId,userType
     * @return
     */
    List<OrderCommentReplyDO> selectCommentMerchantReplyByCommentIdAndUserType(@Param("commentId") Integer commentId,
                                                                               @Param("userType") Integer userType);


    /**
     * 分页获取评论回复
     * @param orderCommentReplyPageDTO
     * @return
     */
    List<OrderCommentReplyDO> selectCommentReplyPage(OrderCommentReplyPageDTO orderCommentReplyPageDTO);


    /**
     * 根据评论 id 和用户类型获取评论回复总数
     * @param commentId,userType
     * @return
     */
    int selectCommentReplyTotalCountByCommentId(@Param("commentId") Integer commentId,
                                                @Param("userType") Integer userType);


    /**
     * 根据评论 id 查询最新的商家回复
     * @param commentIds
     * @return
     */
    List<OrderCommentReplyDO> selectCommentNewMerchantReplyByCommentIds(@Param("commentIds") Collection<Integer> commentIds,
                                                                        @Param("userType") Integer userType);

}
