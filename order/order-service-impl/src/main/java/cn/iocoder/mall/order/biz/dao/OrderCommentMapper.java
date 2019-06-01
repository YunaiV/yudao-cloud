package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.api.bo.OrderCommentReplyCreateBO;
import cn.iocoder.mall.order.api.dto.OrderCommentCreateDTO;
import cn.iocoder.mall.order.api.dto.OrderCommentPageDTO;
import cn.iocoder.mall.order.biz.dataobject.OrderCommentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
     * 根据 sku id 分页查询评论
     * @param productSkuId
     * @param offset
     * @param limit
     * @return
     */
    List<OrderCommentDO> selectCommentPage(@Param("productSkuId") Integer productSkuId,
                                           @Param("offset") Integer offset,
                                           @Param("limit") Integer limit);


    /**
     * 根据评论 id 查询评论详情
     * @param id
     * @return
     */
    OrderCommentDO selectCommentInfoByCommentId(@Param("id") Integer id);



}
