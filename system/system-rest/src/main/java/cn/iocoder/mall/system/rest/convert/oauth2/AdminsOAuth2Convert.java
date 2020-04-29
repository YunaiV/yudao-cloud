package cn.iocoder.mall.system.rest.convert.oauth2;

import cn.iocoder.mall.system.biz.bo.admin.AdminBO;
import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AuthenticateBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2UsernameAuthenticateDTO;
import cn.iocoder.mall.system.rest.request.oauth2.AdminsOAuth2UsernameAuthenticateRequest;
import cn.iocoder.mall.system.rest.response.oauth2.AdminsOAuth2AuthenticateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminsOAuth2Convert {

    AdminsOAuth2Convert INSTANCE = Mappers.getMapper(AdminsOAuth2Convert.class);

    OAuth2UsernameAuthenticateDTO convert(AdminsOAuth2UsernameAuthenticateRequest bean);

    @Mapping(source = "adminBO", target = "admin")
    @Mapping(source = "accessTokenBO.id", target = "token.accessToken")
    @Mapping(source = "accessTokenBO.refreshToken", target = "token.refreshToken")
    @Mapping(source = "accessTokenBO.expiresTime", target = "token.expiresTime")
    AdminsOAuth2AuthenticateResponse convert(AdminBO adminBO, OAuth2AuthenticateBO accessTokenBO);

}
