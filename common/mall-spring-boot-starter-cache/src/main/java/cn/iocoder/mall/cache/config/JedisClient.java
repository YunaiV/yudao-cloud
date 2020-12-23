package cn.iocoder.mall.cache.config;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


@Component
public class JedisClient {

    @Resource
    private static JedisSentinelPool jedisSentinelPool;

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return "";
    }

    public static boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            String ret = jedis.set(key, value);
            return "ok".equalsIgnoreCase(ret);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public static boolean set(String key, String value, int seconds) {
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            String ret = jedis.set(key, value);
            jedis.expire(key, seconds);
            return "ok".equalsIgnoreCase(ret);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public static boolean del(String key) {
        Long removedSize = 0L;
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
            removedSize = jedis.del(key);
        } catch (Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return removedSize > 0;
    }

}
