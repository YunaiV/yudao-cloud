package cn.iocoder.mall.system.rest.convert.oauth2;

import cn.iocoder.mall.system.biz.bo.user.UserAuthenticateBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2MobileCodeAuthenticateDTO;
import cn.iocoder.mall.system.rest.request.oauth2.UsersOAuth2MobileCodeAuthenticateRequest;
import cn.iocoder.mall.system.rest.response.user.UsersOAuth2AuthenticateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersOAuth2Convert {

    UsersOAuth2Convert INSTANCE = Mappers.getMapper(UsersOAuth2Convert.class);

    OAuth2MobileCodeAuthenticateDTO convert(UsersOAuth2MobileCodeAuthenticateRequest request);

    @Mapping(source = "token.id", target = "token.accessToken")
    UsersOAuth2AuthenticateResponse convert(UserAuthenticateBO userAuthenticateBO);

}
