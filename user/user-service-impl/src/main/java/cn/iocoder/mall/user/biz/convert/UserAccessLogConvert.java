package cn.iocoder.mall.user.biz.convert;

import cn.iocoder.mall.user.biz.dataobject.UserAccessLogDO;
import cn.iocoder.mall.user.api.dto.UserAccessLogAddDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccessLogConvert {

    UserAccessLogConvert INSTANCE = Mappers.getMapper(UserAccessLogConvert.class);

    @Mappings({})
    UserAccessLogDO convert(UserAccessLogAddDTO adminAccessLogAddDTO);

}