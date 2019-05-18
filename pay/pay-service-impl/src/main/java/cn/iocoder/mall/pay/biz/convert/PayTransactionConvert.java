package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.bo.transaction.PayTransactionBO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionCreateDTO;
import cn.iocoder.mall.pay.api.dto.transaction.PayTransactionSubmitDTO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionDO;
import cn.iocoder.mall.pay.biz.dataobject.PayTransactionExtensionDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PayTransactionConvert {

    PayTransactionConvert INSTANCE = Mappers.getMapper(PayTransactionConvert.class);

    @Mappings({})
    PayTransactionDO convert(PayTransactionCreateDTO payTransactionCreateDTO);

    @Mappings({})
    PayTransactionBO convert(PayTransactionDO payTransactionDO);

    @Mappings({})
    List<PayTransactionBO> convertList(List<PayTransactionDO> list);

    @Mappings({})
    PayTransactionExtensionDO convert(PayTransactionSubmitDTO payTransactionSubmitDTO);

}
