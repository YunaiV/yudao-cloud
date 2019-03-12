package cn.iocoder.mall.pay.convert;

import cn.iocoder.mall.pay.api.bo.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.dataobject.PayTransactionExtensionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    @Mappings({})
    PayTransactionDO convert(PayTransactionCreateDTO payTransactionCreateDTO);

    @Mappings({})
    PayTransactionBO convert(PayTransactionDO payTransactionDO);

    @Mappings({})
    PayTransactionExtensionDO convert(PayTransactionSubmitDTO payTransactionSubmitDTO);

}