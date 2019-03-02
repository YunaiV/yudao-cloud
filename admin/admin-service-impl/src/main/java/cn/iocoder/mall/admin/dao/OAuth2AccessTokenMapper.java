package cn.iocoder.mall.admin.dao;

import cn.iocoder.mall.admin.dataobject.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuth2AccessTokenMapper {

    void insert(OAuth2AccessTokenDO entity);

    OAuth2AccessTokenDO selectByTokenId(@Param("id") String id);

    int updateToInvalidByAdminId(@Param("adminId") Integer adminId);

}