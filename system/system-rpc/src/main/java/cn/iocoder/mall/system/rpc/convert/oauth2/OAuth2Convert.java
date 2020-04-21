package cn.iocoder.mall.system.rpc.convert.oauth2;

import cn.iocoder.mall.system.biz.bo.ouath2.OAuth2AccessTokenBO;
import cn.iocoder.mall.system.biz.dto.oatuh2.OAuth2AccessTokenAuthenticateDTO;
import cn.iocoder.mall.system.rpc.request.oauth2.OAuth2AccessTokenAuthenticateRequest;
import cn.iocoder.mall.system.rpc.response.oauth2.OAuth2AccessTokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    OAuth2AccessTokenAuthenticateDTO convert(OAuth2AccessTokenAuthenticateRequest authenticateRequest);

    OAuth2AccessTokenResponse convert(OAuth2AccessTokenBO accessTokenBO);

}
