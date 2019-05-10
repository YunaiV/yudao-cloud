package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.dto.AccessLogAddDTO;
import cn.iocoder.mall.admin.api.dto.ExceptionLogAddDTO;
import cn.iocoder.mall.admin.dataobject.AccessLogDO;
import cn.iocoder.mall.admin.dataobject.ExceptionLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccessLogConvert {

    AccessLogConvert INSTANCE = Mappers.getMapper(AccessLogConvert.class);

    @Mappings({})
    AccessLogDO convert(AccessLogAddDTO accessLogAddDTO);

    @Mappings({})
    ExceptionLogDO convert(ExceptionLogAddDTO exceptionLogAddDTO);

}
