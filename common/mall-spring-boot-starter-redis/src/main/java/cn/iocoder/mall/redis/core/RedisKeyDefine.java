package cn.iocoder.mall.redis.core;

import java.time.Duration;

/**
 * Redis Key 定义类
 */
public class RedisKeyDefine {

    public enum KeyTypeEnum {

        STRING,
        LIST,
        HASH,
        SET,
        ZSET,
        STREAM,
        PUBSUB;

    }

    /**
     * 过期时间 - 永不过期
     */
    public static final Duration TIMEOUT_FOREVER = null;

    /**
     * Key 模板
     */
    private final String keyTemplate;
    /**
     * Key 类型的枚举
     */
    private final KeyTypeEnum keyType;
    /**
     * Value 类型
     *
     * 如果是使用分布式锁，设置为 {@link java.util.concurrent.locks.Lock} 类型
     */
    private final Class valueType;
    /**
     * 过期时间
     *
     * 为空时，表示永不过期 {@link #TIMEOUT_FOREVER}
     */
    private final Duration timeout;

    public RedisKeyDefine(String keyTemplate, KeyTypeEnum keyType, Class valueType, Duration timeout) {
        this.keyTemplate = keyTemplate;
        this.keyType = keyType;
        this.valueType = valueType;
        this.timeout = timeout;
    }

    public String getKeyTemplate() {
        return keyTemplate;
    }

    public KeyTypeEnum getKeyType() {
        return keyType;
    }

    public Class getValueType() {
        return valueType;
    }

    public Duration getTimeout() {
        return timeout;
    }

}
