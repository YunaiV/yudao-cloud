package cn.iocoder.mall.system.biz.convert;

import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.bo.user.UserBO;
import cn.iocoder.mall.system.biz.dataobject.user.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(source = "userBO", target = "user")
    @Mapping(source = "accessTokenBO", target = "token")
    UserAuthenticateBO convert(UserBO userBO, OAuth2AccessTokenBO accessTokenBO);

    UserBO convert(UserDO userDO);

}
