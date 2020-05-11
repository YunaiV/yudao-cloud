package cn.iocoder.mall.cache.config;


import org.redisson.Redisson;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SentinelServersConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class RedissonClient {

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.sentinel.nodes}")
    private String nodes;

    /**
     * 哨兵模式 redisson 客户端
     * @return
     */
    @Bean
    public org.redisson.api.RedissonClient redissonClient() {
        Config config = new Config();
        List<String> nodes = Arrays.asList(this.nodes.split(","));
        List<String> newNodes = new ArrayList(nodes.size());
        nodes .forEach((index) -> newNodes.add(
                index.startsWith("redis://") ? index : "redis://" + index));

        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(newNodes.toArray(new String[3]))
                .setMasterName(this.master)
                .setReadMode(ReadMode.SLAVE) ;

        serverConfig.setDatabase(this.database);
        return Redisson.create(config);
    }
}
