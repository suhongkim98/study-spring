package com.studyspring.ws.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.studyspring.ws.websocket.dto.ChatMessage;

@PropertySource("classpath:/application.properties")
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisHost, redisPort); // 지정한 호스트와 포트로 레디스 연결
		return lettuceConnectionFactory;
	}
	@Bean
	public RedisTemplate<String, String> stringValueRedisTemplate() {
		//String 객체 publish에 사용되는 템플릿
		/*
		 * Autowired로 부여한 후 
		 * stringValueRedisTemplate.convertAndSend("ch1","hello"); //ch1채널을 구독하고 있는 Subscriber들에게 hello라는 메시지를 전송한다.
		 * 와 같은 형태로 publish한다.
		 */
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
    @Bean
    public RedisTemplate<String, ChatMessage> chatRedisTemplate() {
    	//ChatMessage 객체 publish에 사용되는 템플릿
        RedisTemplate<String, ChatMessage> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(ChatMessage.class)); //json
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
	@Bean
	public RedisMessageListenerContainer redisContainer() {
		// redis 컨테이너를 생성한다.
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory());
		return container;
	}
}
