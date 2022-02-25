package com.sheep.sheepoj. configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



@Configuration
public class RedisConfig {
    /*
     * @description：处理redisTemplate序列化问题，它本身是二进制存储的，所以不具有序列化功能
     * @param factory:  redis的连接工厂
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
     * @author Xueyang
     * @mood ✩ᏊꈍꈊꈍᏊ
     * @date 2022/2/25 17:13
     */
    @Bean
    public RedisTemplate<String,Object>redisTemplete(RedisConnectionFactory factory){
        RedisTemplate<String, Object> stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringObjectRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        stringObjectRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
        stringObjectRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        stringObjectRedisTemplate.setConnectionFactory(factory);
        return stringObjectRedisTemplate;
    }
}