package cn.iocoder.mall.tradeservice.dal.mysql.mapper.order;

import cn.iocoder.mall.mybatis.core.query.QueryWrapperX;
import cn.iocoder.mall.mybatis.core.util.PageUtil;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderDO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderPageReqDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeOrderMapper extends BaseMapper<TradeOrderDO> {

    default IPage<TradeOrderDO> selectPage(TradeOrderPageReqDTO pageReqDTO) {
        return selectPage(PageUtil.build(pageReqDTO, pageReqDTO.getSorts()),
                new QueryWrapperX<TradeOrderDO>().eqIfPresent("user_id", pageReqDTO.getUserId())
                        .eqIfPresent("status", pageReqDTO.getOrderStatus()));
    }

}
