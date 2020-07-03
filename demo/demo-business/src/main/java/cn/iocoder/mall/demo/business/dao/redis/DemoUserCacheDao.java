package cn.iocoder.mall.demo.business.dao.redis;

import cn.iocoder.mall.demo.business.cacheobject.user.DemoUserCacheObject;
import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DemoUserCacheDao {

    private static final String KEY_PREFIX = "user_";

    @Resource(name = "redisTemplate")
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ValueOperations<String, String> operations;

    private static String buildKey(Integer id) {
        return KEY_PREFIX + id;
    }

    public DemoUserCacheObject get(Integer id) {
        return JSON.parseObject(operations.get(buildKey(id)), DemoUserCacheObject.class);
    }

    public void set(Integer id, DemoUserCacheObject value) {
        operations.set(buildKey(id), JSON.toJSONString(value));
    }

}
