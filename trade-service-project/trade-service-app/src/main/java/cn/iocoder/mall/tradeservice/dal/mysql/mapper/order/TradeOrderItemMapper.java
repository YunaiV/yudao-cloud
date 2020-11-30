package cn.iocoder.mall.tradeservice.dal.mysql.mapper.order;

import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderItemDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TradeOrderItemMapper extends BaseMapper<TradeOrderItemDO> {

    // TODO 芋艿：后续重构到基础库去支持
    default void insertList(List<TradeOrderItemDO> entities) {
        entities.forEach(this::insert);
    }

    default List<TradeOrderItemDO> selectListByOrderIds(Collection<Integer> orderIds) {
        return selectList(new QueryWrapper<TradeOrderItemDO>().in("order_id", orderIds));
    }

    default int updateListByOrderId(TradeOrderItemDO update, Integer orderId, Integer whereStatus) {
        return update(update, new QueryWrapper<TradeOrderItemDO>().eq("order_id", orderId)
            .eq("status", whereStatus));
    }

}
