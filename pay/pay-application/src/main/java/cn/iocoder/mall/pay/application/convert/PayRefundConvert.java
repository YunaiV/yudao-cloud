package cn.iocoder.mall.pay.application.convert;

import cn.iocoder.mall.pay.api.bo.PayRefundBO;
import cn.iocoder.mall.pay.application.vo.admins.AdminsPayRefundDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PayRefundConvert {

    PayRefundConvert INSTANCE = Mappers.getMapper(PayRefundConvert.class);

    @Mappings({})
    List<AdminsPayRefundDetailVO> convertList(List<PayRefundBO> refunds);

}
