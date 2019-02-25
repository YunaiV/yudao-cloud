package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.service.api.bo.UserBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    UserBO convert(UserDO userDO);

}