package cn.iocoder.mall.system.rpc.convert;

import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.rpc.request.system.AccessLogAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogConvert {

    SystemLogConvert INSTANCE = Mappers.getMapper(SystemLogConvert.class);

    AccessLogAddDTO convert(AccessLogAddRequest accessLogAddRequest);

}
