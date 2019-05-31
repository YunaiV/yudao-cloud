package cn.iocoder.mall.admin.convert;

import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AccessTokenBO;
import cn.iocoder.mall.admin.api.bo.oauth2.OAuth2AuthenticationBO;
import cn.iocoder.mall.admin.dataobject.OAuth2AccessTokenDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-31T18:10:14+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_121 (Oracle Corporation)"
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

        oAuth2AuthenticationBO.setUserId( oauth2AccessTokenDO.getUserId() );
        oAuth2AuthenticationBO.setUserType( oauth2AccessTokenDO.getUserType() );

        return oAuth2AuthenticationBO;
    }
}
