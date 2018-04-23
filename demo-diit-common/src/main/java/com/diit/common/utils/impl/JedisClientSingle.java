package com.diit.common.utils.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.diit.common.utils.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @author lishw
 * @create 2017-01-18
 */
public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    @Value("${spring.redis.password}")
    private String password;

    private Jedis getResource() {
        Jedis resource = jedisPool.getResource();
        if (StringUtils.isBlank(password)) {
            return resource;
        } else {
            resource.auth(password);
            return resource;
        }
    }

    @Override
    public String get(String key) {

        Jedis resource = getResource();

        String string = resource.get(key);

        resource.close();

        return string;

    }

    @Override
    public String set(String key, String value) {

        Jedis resource = getResource();

        String string = resource.set(key, value);

        resource.close();

        return string;

    }

    @Override
    public String hget(String hkey, String key) {

        Jedis resource = getResource();

        String string = resource.hget(hkey, key);

        resource.close();

        return string;

    }

    @Override
    public long hset(String hkey, String key, String value) {

        Jedis resource = getResource();

        Long hset = resource.hset(hkey, key, value);

        resource.close();

        return hset;

    }

    @Override
    public long incr(String key) {

        Jedis resource = getResource();

        Long incr = resource.incr(key);

        resource.close();

        return incr;

    }

    @Override
    public long expire(String key, Integer second) {

        Jedis resource = getResource();

        Long expire = resource.expire(key, second);

        resource.close();

        return expire;

    }

    @Override
    public long ttl(String key) {

        Jedis resource = getResource();

        Long ttl = resource.ttl(key);

        resource.close();

        return ttl;
    }

    @Override
    public long del(String key) {

        Jedis resource = getResource();

        Long del = resource.del(key);

        resource.close();

        return del;
    }

    @Override
    public long hdel(String hkey, String key) {

        Jedis resource = getResource();

        Long hdel = resource.hdel(hkey, key);

        resource.close();

        return hdel;
    }
}
