package cn.iocoder.mall.order.biz.dao.order;

import cn.iocoder.mall.order.biz.dataobject.OrderCancelDO;
import org.springframework.stereotype.Repository;

/**
 * 订单取消 mapper
 *
 * @author Sin
 * @time 2019-03-30 16:27
 */
@Repository
public interface OrderCancelMapper {

    int insert(OrderCancelDO orderCancelDO);

}
