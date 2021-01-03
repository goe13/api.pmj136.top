package top.pmj136.api.component;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 彭明久
 * @since 2020-09-29
 */
@Component
public class RedisHelper {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, Object data) {
        redisTemplate.opsForValue().set(key, data);
    }

    public void set(String key, Object data, long expire) {
        redisTemplate.opsForValue().set(key, data, expire, TimeUnit.SECONDS);
    }


    public Object get(String key) {
        if (key == null) return null;
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        if (key == null) return false;
        return redisTemplate.hasKey(key);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
}
