package cn.iocoder.mall.tradeservice.dal.mysql.mapper.order;

import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderItemDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeOrderItemMapper extends BaseMapper<TradeOrderItemDO> {

    // TODO 芋艿：后续重构到基础库去支持
    default void insertList(List<TradeOrderItemDO> entities) {
        entities.forEach(this::insert);
    }

}
