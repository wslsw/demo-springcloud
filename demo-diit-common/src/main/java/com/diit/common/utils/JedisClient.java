package com.diit.common.utils;

/**
 * Jedis 接口
 *
 * @author lishw
 * @create 2017-01-18
 */
public interface JedisClient {

    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, Integer second);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);

}
