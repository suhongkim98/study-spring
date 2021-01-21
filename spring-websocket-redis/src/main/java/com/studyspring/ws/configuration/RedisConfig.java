package com.studyspring.ws.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.studyspring.ws.redis.RedisMessageStringSubscriber;

@PropertySource("classpath:/application.properties")
@Configuration
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisHost, redisPort);
		return lettuceConnectionFactory;
	}
	@Bean
	public RedisTemplate<String, String> stringValueRedisTemplate() {
		//메시지 publish할 때 쓰이는 템플릿 정의
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
	/*
    @Bean
    public RedisTemplate<String, CoffeeDTO> coffeeDTORedisTemplate() {

        RedisTemplate<String, CoffeeDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(CoffeeDTO.class)); //json
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }
    */
	@Bean
	public RedisMessageListenerContainer redisContainer() {
		// redis 컨테이너 정의
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(redisConnectionFactory());
		
		// 리스너 추가, 구독하겠다는 의미
		container.addMessageListener(messageStringListener(), topic01()); // topic01 채널로 오는 메시지는 messageStringLister에서 처리하겠다.
		//
		return container;
	}
	
	@Bean
	public MessageListenerAdapter messageStringListener() {
		// 메시지 수신받을 리스너 생성
		return new MessageListenerAdapter(new RedisMessageStringSubscriber());
	}
	/*
	@Bean
	public MessageListenerAdapter messageDTOListener() {
		// 메시지 수신받을 리스너 생성
		return new MessageListenerAdapter(new RedisMessageDTOSubscriber());
	}
	*/
	@Bean
	public ChannelTopic topic01() {
		//메시지 주고받을 채널 생성
		return new ChannelTopic("ch1");
	}
	/*
	@Bean
	public ChannelTopic topic02() {
		//메시지 주고받을 채널 생성
		return new ChannelTopic("ch2");
	}
	*/
}
