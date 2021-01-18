package com.studyspring.ws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.studyspring.ws.websocket.WebSocketChatHandler;

@Configuration
@EnableWebSocket
//websocketChatHander 활성화를 위한 설정파일
public class WebSocketConfig implements WebSocketConfigurer{
	private WebSocketChatHandler webSocketChatHandler;
	@Autowired
	public WebSocketConfig(WebSocketChatHandler webSocketChatHandler) {
		this.webSocketChatHandler = webSocketChatHandler;
	}
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		//websocketChatHander를 등록하고 엔드포인트 설정
		registry.addHandler(webSocketChatHandler, "/socket/chat").setAllowedOrigins("*");
	}
	
	
}
