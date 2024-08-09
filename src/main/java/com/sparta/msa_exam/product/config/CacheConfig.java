package com.sparta.msa_exam.product.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching // 캐싱 활성화
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory){

        // Redis 관련 설정을 모아두는 클래스
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig() // 캐시의 기본 구성 설정
                .disableCachingNullValues() // Null 값은 캐시에 저장 X
                .entryTtl(Duration.ofSeconds(60)) // TTL(Time To Live) 만료시간 설정
                .computePrefixWith(CacheKeyPrefix.simple()) // 캐시에 대해 접두사 설정
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(RedisSerializer.java())); // 캐시에 저장되는 값의 직렬화 방법 설정

        return RedisCacheManager
                .builder(redisConnectionFactory) // Redis 서버에 연결하는 방법 정의
                .cacheDefaults(configuration) // 위에서 만든 기본 구성 세팅
                // .withInitialCacheConfigurations() 이 메서드는 캐시에 대해 별도의 설정을 하는 기능
                .build();
    }
}
