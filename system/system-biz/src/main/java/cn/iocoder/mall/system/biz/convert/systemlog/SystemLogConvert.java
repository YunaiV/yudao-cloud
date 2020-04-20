package cn.iocoder.mall.system.biz.convert.systemlog;

import cn.iocoder.mall.system.biz.dataobject.system.AccessLogDO;
import cn.iocoder.mall.system.biz.dto.system.AccessLogAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SystemLogConvert {

    SystemLogConvert INSTANCE = Mappers.getMapper(SystemLogConvert.class);

    AccessLogDO convert(AccessLogAddDTO accessLogAddDTO);

}
