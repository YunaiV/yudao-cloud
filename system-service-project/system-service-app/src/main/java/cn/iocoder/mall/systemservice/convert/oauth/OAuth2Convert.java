package cn.iocoder.mall.systemservice.convert.oauth;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2AccessTokenDO;
import cn.iocoder.mall.systemservice.rpc.oauth.dto.OAuth2AccessTokenRespDTO;
import cn.iocoder.mall.systemservice.service.oauth.bo.OAuth2AccessTokenBO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OAuth2Convert {

    OAuth2Convert INSTANCE = Mappers.getMapper(OAuth2Convert.class);

    @Mapping(source = "id", target = "accessToken")
    OAuth2AccessTokenBO convert(OAuth2AccessTokenDO bean);

    OAuth2AccessTokenRespDTO convert(OAuth2AccessTokenBO bean);

}
