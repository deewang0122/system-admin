package com.dee.studyadmin.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtils {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 添加key,value
     * @param key
     * @param value
     * @param expireTime
     */
    public void set(String key, String value, Long expireTime) {
        if (Objects.isNull(expireTime)) {
            stringRedisTemplate.opsForValue().set(key, value);
        } else {
            stringRedisTemplate.opsForValue().set(key, value, expireTime.longValue(), TimeUnit.SECONDS);
        }
    }

    /**
     * refresh key,value
     * @param key
     * @param value
     * @param expireTime
     */
    public void refresh(String key, String value, Long expireTime) {
        if (Objects.isNull(expireTime)) {
            stringRedisTemplate.opsForValue().getAndSet(key, value);
        } else {
            stringRedisTemplate.expire(key, Duration.ofSeconds(expireTime.longValue()));
        }
    }

    /**
     * 根据key查询value
     * @param key
     * @return
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key删除
     * @param key
     * @return
     */
    public Boolean delete(String key) {
        Boolean delete = stringRedisTemplate.opsForValue().getOperations().delete(key);
        return delete;
    }

    /**
     * 查询key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        Boolean b =stringRedisTemplate.hasKey(key);
        return b;
    }

    /**
     * 根据key覆盖value
     * @param key
     * @param value
     * @param expireTime
     */
    public void getAndSet(String key, String value, Long expireTime) {
        stringRedisTemplate.opsForValue().getAndSet(key, value);
        if (Objects.nonNull(expireTime)) {
            stringRedisTemplate.expire(key, expireTime.longValue(), TimeUnit.SECONDS);
        }
    }

    /**
     * 根据关键字查询所有key
     * @param pattern
     * @return
     */
    public Set<String> findKeysByPattern(String pattern) {
        Set<String> keys = stringRedisTemplate.keys(pattern + "*");
        return keys;
    }
}
