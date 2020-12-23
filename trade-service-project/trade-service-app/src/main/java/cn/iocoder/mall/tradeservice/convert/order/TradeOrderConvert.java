package cn.iocoder.mall.tradeservice.convert.order;

import cn.iocoder.common.framework.vo.PageResult;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderDO;
import cn.iocoder.mall.tradeservice.dal.mysql.dataobject.order.TradeOrderItemDO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderItemRespDTO;
import cn.iocoder.mall.tradeservice.rpc.order.dto.TradeOrderRespDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TradeOrderConvert {

    TradeOrderConvert INSTANCE = Mappers.getMapper(TradeOrderConvert.class);

    TradeOrderRespDTO convert(TradeOrderDO bean);

    @Mapping(source = "records", target = "list")
    PageResult<TradeOrderRespDTO> convertPage(IPage<TradeOrderDO> page);

    List<TradeOrderItemRespDTO> convertList(List<TradeOrderItemDO> list);

    TradeOrderItemRespDTO convert(TradeOrderItemDO bean);

}
