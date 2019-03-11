package cn.iocoder.mall.user.dao;

import cn.iocoder.mall.user.dataobject.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2RefreshTokenMapper {

    void insert(OAuth2RefreshTokenDO entity);

    void updateToInvalidByUserId(@Param("userId") Integer userId);

}