package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.OAuth2AccessTokenDO;
import cn.iocoder.mall.user.service.api.bo.OAuth2AccessTokenBO;
import cn.iocoder.mall.user.service.api.bo.OAuth2AuthenticationBO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-05T10:35:05+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class OAuth2ConvertImpl implements OAuth2Convert {

    @Override
    public OAuth2AccessTokenBO convertToAccessToken(OAuth2AccessTokenDO oauth2AccessTokenDO) {
        if ( oauth2AccessTokenDO == null ) {
            return null;
        }

        OAuth2AccessTokenBO oAuth2AccessTokenBO = new OAuth2AccessTokenBO();

        oAuth2AccessTokenBO.setAccessToken( oauth2AccessTokenDO.getId() );
        oAuth2AccessTokenBO.setRefreshToken( oauth2AccessTokenDO.getRefreshToken() );

        return oAuth2AccessTokenBO;
    }

    @Override
    public OAuth2AuthenticationBO convertToAuthentication(OAuth2AccessTokenDO oauth2AccessTokenDO) {
        if ( oauth2AccessTokenDO == null ) {
            return null;
        }

        OAuth2AuthenticationBO oAuth2AuthenticationBO = new OAuth2AuthenticationBO();

        oAuth2AuthenticationBO.setUid( oauth2AccessTokenDO.getUid() );

        return oAuth2AuthenticationBO;
    }
}
