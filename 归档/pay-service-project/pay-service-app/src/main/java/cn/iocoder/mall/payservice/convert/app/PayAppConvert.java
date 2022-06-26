package cn.iocoder.mall.payservice.convert.app;

import cn.iocoder.mall.payservice.dal.mysql.dataobject.app.PayAppDO;
import cn.iocoder.mall.payservice.rpc.app.dto.PayAppRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayAppConvert {

    PayAppConvert INSTANCE = Mappers.getMapper(PayAppConvert.class);

    PayAppRespDTO convert(PayAppDO bean);

}

