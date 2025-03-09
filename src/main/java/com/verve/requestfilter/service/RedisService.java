package com.verve.requestfilter.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class RedisService {
    
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String REDIS_KEY_PREFIX = "unique_ids:";
    private static final Duration EXPIRATION = Duration.ofMinutes(2);

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isNewRequest(int id) {
        String key = REDIS_KEY_PREFIX + System.currentTimeMillis() / 60000; // Grouping by minute
        Long added = redisTemplate.opsForSet().add(key, String.valueOf(id));
        redisTemplate.expire(key, EXPIRATION);  // Ensure TTL
        return added != null && added > 0;        // Returns true if ID is unique
    }

    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
