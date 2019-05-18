package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.dataobject.OAuth2AccessTokenDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    @Mappings({
            @Mapping(source = "id", target = "accessToken")
    })
    OAuth2AccessTokenBO convertToAccessToken(OAuth2AccessTokenDO oauth2AccessTokenDO);

    default OAuth2AccessTokenBO convertToAccessTokenWithExpiresIn(OAuth2AccessTokenDO oauth2AccessTokenDO) {
        return this.convertToAccessToken(oauth2AccessTokenDO)
                .setExpiresIn(Math.max((int) ((oauth2AccessTokenDO.getExpiresTime().getTime() - System.currentTimeMillis()) / 1000), 0));
    }

    @Mappings({})
    OAuth2AuthenticationBO convertToAuthentication(OAuth2AccessTokenDO oauth2AccessTokenDO);


}
