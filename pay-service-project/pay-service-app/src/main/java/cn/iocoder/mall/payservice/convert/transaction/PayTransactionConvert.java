package cn.iocoder.mall.payservice.convert.transaction;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionDO;
import cn.iocoder.mall.payservice.dal.mysql.dataobject.transaction.PayTransactionExtensionDO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionCreateReqDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionRespDTO;
import cn.iocoder.mall.payservice.rpc.transaction.dto.PayTransactionSubmitReqDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    PayTransactionDO convert(PayTransactionCreateReqDTO bean);

    PayTransactionExtensionDO convert(PayTransactionSubmitReqDTO bean);

    PayTransactionRespDTO convert(PayTransactionDO bean);

}
