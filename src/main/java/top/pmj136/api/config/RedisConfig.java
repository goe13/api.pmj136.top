package top.pmj136.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

/**
 * @author 彭明久
 * @since 2020-09-29
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${api.cache.expire}")
    private Integer hours;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(hours));

        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
    }

}
