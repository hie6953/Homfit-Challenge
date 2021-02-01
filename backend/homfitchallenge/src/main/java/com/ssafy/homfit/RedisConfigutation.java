package com.ssafy.homfit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;



@Configuration
public class RedisConfigutation {

	
	private @Value("${spring.redis.host}") String redisHost;
	private @Value("${spring.redis.port}") int redisPort;
	private @Value("${spring.redis.password}") String redisPassword;
	
	/**
	 * @author 황다희
	 * RedisConnectionFactory 설정 - Redis 접속
	 * connection 관리 - LettuceConnectionFactory 사용
	 * 
	 */	
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(redisHost); //host
        redisStandaloneConfiguration.setPort(redisPort); //port
        redisStandaloneConfiguration.setPassword(redisPassword); //password    
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		return lettuceConnectionFactory; 
	}
	
	
	/**
	 * RedisTemplate 설정 - Redis데이터 쉽게 접근
	 * key, value Serializer설정
	 * GenericJackson2JsonRedisSerializer()로 세팅시 - @class데이터 붙음, 
	 *  
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer()); //key설정 - string
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //value 직렬화설정 - 객체가 json 형태로 변환
		return redisTemplate;
	}
	

}
