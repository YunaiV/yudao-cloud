package cn.iocoder.mall.system.biz.convert.systemlog;

import cn.iocoder.mall.system.biz.dataobject.systemlog.AccessLogDO;
import cn.iocoder.mall.system.biz.dataobject.systemlog.ExceptionLogDO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import cn.iocoder.mall.system.biz.dto.system.ExceptionLogAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogConvert {

    SystemLogConvert INSTANCE = Mappers.getMapper(SystemLogConvert.class);

    AccessLogDO convert(AccessLogAddDTO bean);

    ExceptionLogDO convert(ExceptionLogAddDTO bean);

}
