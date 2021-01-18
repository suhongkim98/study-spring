package com.studyspring.ws.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspring.ws.websocket.dto.ChatMessage;
import com.studyspring.ws.websocket.service.ChatService;


@Component
public class WebSocketChatHandler extends TextWebSocketHandler {
	private ObjectMapper objectMapper;
	private ChatService chatService;
	@Autowired
	public WebSocketChatHandler(ChatService chatService) {
		this.chatService = chatService;
		this.objectMapper = new ObjectMapper();
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//여러 클라이언트가 발송한 메시지를 받아 처리해줄 handler
		//모든 요청은 여기로 들어온다.
		String payload = message.getPayload();
		System.out.println(payload);
		ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class); // 클라이언트로부터 메시지를 받아 채팅 메시지 객체로 변환 ( json -> java 객체 )
		chatService.handleAction(session, chatMessage); // chatService로 넘긴다.
	}
}
