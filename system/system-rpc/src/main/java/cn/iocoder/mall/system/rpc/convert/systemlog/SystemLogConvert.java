package cn.iocoder.mall.system.rpc.convert.systemlog;

import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;
import cn.iocoder.mall.system.rpc.request.systemlog.AccessLogAddRequest;
import cn.iocoder.mall.system.rpc.request.systemlog.ExceptionLogAddRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogConvert {

    SystemLogConvert INSTANCE = Mappers.getMapper(SystemLogConvert.class);

    AccessLogAddDTO convert(AccessLogAddRequest accessLogAddRequest);

    ExceptionLogAddDTO convert(ExceptionLogAddRequest exceptionLogAddRequest);

}
