package com.sheep.sheepoj.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.parameters.P;

public class RedisUtils {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public Object getValue(String key)
    {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }
}
