package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.biz.dataobject.OrderReturnDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单退货 mapper
 *
 * @author Sin
 * @time 2019-03-30 15:36
 */
@Repository
public interface OrderReturnMapper {

    /**
     * 插入 - 退货信息
     *
     * @param orderReturnDO
     * @return
     */
    int insert(OrderReturnDO orderReturnDO);

    /**
     * 更新 - 根据 orderId
     *
     * @param orderReturnDO
     * @return
     */
    int updateByOrderId(OrderReturnDO orderReturnDO);

    /**
     * 查询 - 根据 orderId
     *
     * @param orderId
     * @return
     */
    OrderReturnDO selectByOrderId(
            @Param("orderId") Integer orderId
    );
}
