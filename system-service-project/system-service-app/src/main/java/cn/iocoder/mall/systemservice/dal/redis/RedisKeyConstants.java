package cn.iocoder.mall.systemservice.dal.redis;

import cn.iocoder.mall.redis.core.RedisKeyDefine;
import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2AccessTokenDO;

import java.time.Duration;

import static cn.iocoder.mall.redis.core.RedisKeyDefine.KeyTypeEnum.STRING;

/**
 * Redis Key 枚举类
 *
 * 通过将项目中的 Key 枚举在该类中，方便统一管理。
 */
public interface RedisKeyConstants {

    /**
     * {@link OAuth2AccessTokenDO} 的缓存
     *
     * key 的 format 的参数是 [{@link OAuth2AccessTokenDO#getId()}]
     */
    RedisKeyDefine OAUTH2_ACCESS_TOKEN = new RedisKeyDefine("oauth2_access_token:%s", STRING, OAuth2AccessTokenDO.class, Duration.ofHours(2));

}
