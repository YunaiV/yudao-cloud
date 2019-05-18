package cn.iocoder.mall.user.biz.convert;

import cn.iocoder.mall.user.api.bo.user.UserAuthenticationBO;
import cn.iocoder.mall.user.api.bo.UserBO;
import cn.iocoder.mall.user.api.dto.UserUpdateDTO;
import cn.iocoder.mall.user.biz.dataobject.UserDO;
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
    UserAuthenticationBO convert2(UserDO userDO);

    @Mappings({})
    UserDO convert(UserUpdateDTO userUpdateDTO);

    @Mappings({})
    List<UserBO> convert(List<UserDO> userDOs);

}
