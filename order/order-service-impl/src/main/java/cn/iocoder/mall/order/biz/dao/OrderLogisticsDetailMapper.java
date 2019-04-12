package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.biz.dataobject.OrderLogisticsDetailDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 订单物流 - 物流详细信息
 *
 * @author Sin
 * @time 2019-04-12 21:35
 */
@Repository
public interface OrderLogisticsDetailMapper {

    /**
     * 插入
     *
     * @param orderLogisticsDetailDO
     * @return
     */
    int insert(OrderLogisticsDetailDO orderLogisticsDetailDO);

    /**
     * 查询 - 根据 物流id
     *
     * @param orderLogisticsId
     * @return
     */
    List<OrderLogisticsDetailDO> selectByOrderLogisticsId(
            @Param("orderLogisticsId") Integer orderLogisticsId
    );

    /**
     * 查询 - 根据 物流ids
     *
     * @param orderLogisticsIds
     * @return
     */
    List<OrderLogisticsDetailDO> selectByOrderLogisticsIds(
            @Param("orderLogisticsIds") Collection<Integer> orderLogisticsIds
    );
}
