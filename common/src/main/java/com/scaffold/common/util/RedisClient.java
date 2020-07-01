package com.scaffold.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaffold.common.constant.Enum.Encoding;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author danyiran
 * @create 2020/7/1 22:22
 */
@Slf4j
@Component
public class RedisClient {

    @Autowired(required = false)
    private ShardedJedisPool pool;

    public synchronized void setShardedJedisPool(ShardedJedisPool pool) {
        this.pool = pool;
    }

    public synchronized ShardedJedis getJedis() {
        ShardedJedis jedis = null;
        if (pool != null) {
            jedis = pool.getResource();
            return jedis;
        } else {
            log.error("获取Jedis失败");
        }
        return null;
    }

    /**
     * the ordered set adds or updates the scores of the existing members.
     *
     * @param key
     * @param score
     * @param member
     * @return boolean
     */
    public boolean zaddSorted(final String key, final Integer score, final String member) {
        final ShardedJedis jedis = getJedis();
        try {
            return jedis.zadd(key, score, member) > 0L;

        } catch (final Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * an ordered set of values (in terms of fractions).
     *
     * @param key
     * @param score
     * @return object
     */
    public Object getSorted(final String key, final Integer score) {

        final ShardedJedis jedis = getJedis();
        try {
            final Set<String> memberSet = jedis.zrevrange(key, 0, score);
            return memberSet;
        } catch (final Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * increase acquisition of correlation key words by ordered set.
     *
     * @param key
     * @param fuzzy
     * @param size
     * @return object
     */
    public Object getMatchMember(final String key, final String fuzzy, final int size) {
        final ShardedJedis jedis = getJedis();
        try {

            final ScanParams scanParams = new ScanParams();
            scanParams.count(size);
            scanParams.match("*" + fuzzy + "*");
            final ScanResult<Tuple> memberSet = jedis.zscan(key, "", scanParams);
            return memberSet;
        } catch (final Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;

    }

    /**
     * increase the score of an ordered set of a certain value.
     *
     * @param key
     * @param score
     * @param member
     * @return boolean
     */
    public boolean incrbySocre(final String key, final Integer score, final String member) {
        final ShardedJedis jedis = getJedis();
        try {
            return jedis.zincrby(key, score, member) > 0L;

        } catch (final Exception e) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * add key value pairs in the specified domain
     *
     * @param value
     * @param key
     * @param field
     * @return boolean
     */
    public boolean set(final Object value, final String key, final String field) {

        final ShardedJedis jedis = getJedis();
        try {
            ObjectMapper mapper = new ObjectMapper();
            String valueJsonString = mapper.writeValueAsString(value);
            return jedis.hset(key, field, valueJsonString) > 0L;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public boolean delList(final String key) {
        final ShardedJedis jedis = getJedis();
        try {
            return jedis.del(key.getBytes(Encoding.UTF8.name())) > 0L;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public boolean listSet(final String key, final List<String> value) {
        final ShardedJedis jedis = getJedis();
        try {
            for (final String code : value) {
                jedis.rpush(key.getBytes(Encoding.UTF8.name()), code.getBytes(Encoding.UTF8.name()));
            }
            return true;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public List<String> hasApp(final String key) {
        final ShardedJedis jedis = getJedis();
        try {
            return jedis.lrange(key, 0, -1);
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        final ShardedJedis jedis = getJedis();

        Boolean result = jedis.exists(key);

        return result;
    }

    /**
     * get the value of the corresponding key in the specified domain.
     *
     * @param key
     * @param field
     * @return Object
     */
    public Object get(final String key, final String field, Class clazz) {

        final ShardedJedis jedis = getJedis();
        try {

            if (jedis.hget(key, field) == null) {
                return null;
            }
            String value = jedis.hget(key, field);
            log.info("查询到key:{} - field:{} - result:{}", key, field, value);
            ObjectMapper mapper = new ObjectMapper();
            Object result = mapper.readValue(value, clazz);
            return result;
        } catch (final JedisException e) {
            log.error(e.getMessage());
            return null;
        } catch (JsonParseException e) {
            log.error(e.getMessage());
            return null;
        } catch (JsonMappingException e) {
            log.error(e.getMessage());
            return null;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * delete key value pairs in the specified key name hash table.
     *
     * @param key
     * @param field
     * @return boolean
     */
    public boolean del(final String key, final String field) {

        final ShardedJedis jedis = getJedis();
        try {
            return jedis.hdel(key, field) > 0;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * delete all key value pairs in the specified key key
     *
     * @param key
     * @return boolean
     */
    public boolean mdel(final String key) {

        final ShardedJedis jedis = getJedis();
        try {
            final List<String> keys = scan(key);
            if (keys != null) {
                for (final String k : keys) {
                    del(key, k);
                }
                return true;
            } else {
                return false;
            }
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * Get all keys in the specified key name hash table.
     *
     * @param key
     * @return List
     */
    public List<String> scan(final String key) {

        final ShardedJedis jedis = getJedis();
        try {
            final List<String> list = new ArrayList<String>();
            list.addAll(jedis.hkeys(key));
            return list;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * set value by key、value、seconds
     *
     * @param key
     * @param value
     * @param seconds
     */
    public Boolean setKeyValue(final String key, final String value, final Integer seconds) {

        final ShardedJedis jedis = getJedis();
        try {
            String tt = jedis.set(key, value);
            if ("OK".equals(jedis.set(key, value))) {
                jedis.expire(key, seconds);
                return true;
            }
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * get value by key
     *
     * @param key
     */
    public String getKeyValue(final String key) {

        final ShardedJedis jedis = getJedis();
        try {
            return jedis.get(key);
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    /**
     * set outTime
     *
     * @param key
     * @param seconds
     */
    public Boolean setKeyTimeout(final String key, final Integer seconds) {

        final ShardedJedis jedis = getJedis();
        Long result = null;
        try {
            Boolean hasKey = jedis.exists(key);
            if (hasKey) {
                result = jedis.expire(key, seconds);
            } else {
                result = -1L;
            }
            return result == 1L;
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 模糊匹配 key 值
     *
     * @param key
     * @return
     */
    public Set<String> keys(String key) {
        final ShardedJedis jedis = getJedis();
        try {
            Jedis redis = jedis.getShard("");
            return redis.keys("*" + key + "*");
        } catch (final JedisException e) {
            log.error(e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }
}
