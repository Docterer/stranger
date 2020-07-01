package com.scaffold.common.config;

import com.scaffold.common.config.properties.JedisProperties;
import com.scaffold.common.util.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 22:19
 */
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties(JedisProperties.class)
@ConditionalOnClass(value = {RedisClient.class})
public class RedisConfig {

    @Autowired
    private JedisProperties prop;

    @Bean(name = "shardedJedisPool")
    public ShardedJedisPool shardedJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(prop.getMaxTotal());
        config.setMaxIdle(prop.getMaxIdle());
        config.setMaxWaitMillis(prop.getMaxWaitMillis());
        List<JedisShardInfo> jedisShardInfoList = new ArrayList<>();
        jedisShardInfoList.add(new JedisShardInfo(prop.getUri()));
        return new ShardedJedisPool(config, jedisShardInfoList);
    }

    @Bean
    @ConditionalOnMissingBean(RedisClient.class)
    public RedisClient redisClient(@Qualifier("shardedJedisPool") ShardedJedisPool shardedJedisPool) {
        RedisClient redisClient = new RedisClient();
        redisClient.setShardedJedisPool(shardedJedisPool);
        return redisClient;
    }
}
