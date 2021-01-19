package com.studyspring.ws.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
@Configuration
@EnableWebSocketMessageBroker //Stomp 메시지 브로커 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		//클라이언트에서 stomp이용한 웹소켓 연결을 할 EndPoint를 설정한다.
		registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
	}

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	// subscribe, publish요청 prefix 설정
    	registry.enableSimpleBroker("/topic"); // subscribe prefix
    	registry.setApplicationDestinationPrefixes("/app"); // publish 요청 prefix
    }
	
}
