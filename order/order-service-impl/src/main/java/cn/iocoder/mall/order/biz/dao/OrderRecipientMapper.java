package cn.iocoder.mall.order.biz.dao;

import cn.iocoder.mall.order.biz.dataobject.OrderRecipientDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 订单收件人 信息
 *
 * @author Sin
 * @time 2019-03-31 12:16
 */
@Repository
public interface OrderRecipientMapper {

    /**
     * 插入 - 订单收件人
     *
     * @param orderRecipient
     * @return
     */
    int insert(OrderRecipientDO orderRecipient);

    /**
     * 查询 - 根据 orderId
     *
     * @param orderId
     * @return
     */
    OrderRecipientDO selectByOrderId(
            @Param("orderId") Integer orderId
    );

    /**
     * 查询 - 根据 orderIds
     *
     * @param orderIds
     * @return
     */
    List<OrderRecipientDO> selectByOrderIds(
            @Param("orderIds")Collection<Integer> orderIds
    );

}
