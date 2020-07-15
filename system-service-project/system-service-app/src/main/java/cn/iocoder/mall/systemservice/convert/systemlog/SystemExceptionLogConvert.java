package cn.iocoder.mall.systemservice.convert.systemlog;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.systemlog.SystemExceptionLogDO;
import cn.iocoder.mall.systemservice.rpc.systemlog.dto.SystemExceptionLogCreateDTO;
import cn.iocoder.mall.systemservice.service.systemlog.bo.SystemExceptionLogCreateBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemExceptionLogConvert {

    SystemExceptionLogConvert INSTANCE = Mappers.getMapper(SystemExceptionLogConvert.class);

    SystemExceptionLogDO convert(SystemExceptionLogCreateBO bean);

    SystemExceptionLogCreateBO convert(SystemExceptionLogCreateDTO bean);

}
