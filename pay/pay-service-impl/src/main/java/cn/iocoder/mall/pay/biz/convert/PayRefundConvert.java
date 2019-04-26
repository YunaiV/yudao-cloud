package cn.iocoder.mall.pay.biz.convert;

import cn.iocoder.mall.pay.api.dto.PayRefundSubmitDTO;
import cn.iocoder.mall.pay.biz.dataobject.PayRefundDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayRefundConvert {

    PayRefundConvert INSTANCE = Mappers.getMapper(PayRefundConvert.class);

    @Mappings({})
    PayRefundDO convert(PayRefundSubmitDTO payRefundSubmitDTO);

}
