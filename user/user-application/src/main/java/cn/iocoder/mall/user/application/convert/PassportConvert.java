package cn.iocoder.mall.user.application.convert;

import cn.iocoder.mall.user.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.application.vo.users.UsersAccessTokenVO;
import cn.iocoder.mall.user.application.vo.users.UsersMobileRegisterVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassportConvert {

    PassportConvert INSTANCE = Mappers.getMapper(PassportConvert.class);

    @Mappings({})
    UsersMobileRegisterVO convert(OAuth2AccessTokenBO oauth2AccessTokenBO);

    @Mappings({})
    UsersAccessTokenVO convert2(OAuth2AccessTokenBO result);

}
