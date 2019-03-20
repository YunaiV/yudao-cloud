package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.UserAccessLogDO;
import cn.iocoder.mall.user.service.api.dto.UserAccessLogAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccessLogConvert {

    UserAccessLogConvert INSTANCE = Mappers.getMapper(UserAccessLogConvert.class);

    @Mappings({})
    UserAccessLogDO convert(UserAccessLogAddDTO adminAccessLogAddDTO);

}