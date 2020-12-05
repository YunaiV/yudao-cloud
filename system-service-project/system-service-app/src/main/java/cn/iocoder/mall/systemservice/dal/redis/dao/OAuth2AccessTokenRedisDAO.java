package cn.iocoder.mall.systemservice.dal.redis.dao;

import cn.iocoder.mall.systemservice.dal.mysql.dataobject.oauth.OAuth2AccessTokenDO;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import static cn.iocoder.mall.systemservice.dal.redis.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;

@Repository
public class OAuth2AccessTokenRedisDAO {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public OAuth2AccessTokenDO get(String accessToken) {
        String redisKey = formatKey(accessToken);
        return JSON.parseObject(redisTemplate.opsForValue().get(redisKey), OAuth2AccessTokenDO.class);
    }

    public void set(OAuth2AccessTokenDO accessTokenDO) {
        String redisKey = formatKey(accessTokenDO.getId());
        redisTemplate.opsForValue().set(redisKey, JSON.toJSONString(accessTokenDO), OAUTH2_ACCESS_TOKEN.getTimeout());
    }

    public void delete(String accessToken) {
        String redisKey = formatKey(accessToken);
        redisTemplate.delete(redisKey);
    }

    private static String formatKey(String accessToken) {
        return String.format(OAUTH2_ACCESS_TOKEN.getKeyTemplate(), accessToken);
    }

}
