package cn.iocoder.mall.user.convert;

import cn.iocoder.mall.user.dataobject.OAuth2AccessTokenDO;
import cn.iocoder.mall.user.service.api.dto.OAuth2AccessTokenBO;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    @Mappings({})
    OAuth2AccessTokenBO convert(OAuth2AccessTokenDO oauth2AccessTokenDO);

    default OAuth2AccessTokenBO convertWithExpiresIn(OAuth2AccessTokenDO oauth2AccessTokenDO) {
        OAuth2AccessTokenBO bo = this.convert(oauth2AccessTokenDO);
        bo.setExpiresIn(Math.max((int) ((oauth2AccessTokenDO.getExpiresTime().getTime() - System.currentTimeMillis()) / 1000), 0));
        return bo;
    }

}