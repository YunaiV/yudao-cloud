package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.UserDO;
import cn.iocoder.mall.user.service.api.bo.UserBO;
import cn.iocoder.mall.user.service.api.dto.UserUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mappings({})
    UserBO convert(UserDO userDO);

    @Mappings({})
    UserDO convert(UserUpdateDTO userUpdateDTO);

    @Mappings({})
    List<UserBO> convert(List<UserDO> userDOs);

}