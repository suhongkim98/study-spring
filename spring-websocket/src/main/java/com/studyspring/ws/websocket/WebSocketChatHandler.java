package com.studyspring.ws.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspring.ws.websocket.dto.ChatMessage;
import com.studyspring.ws.websocket.dto.ChatRoom;
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
		String payload = message.getPayload();
		System.out.println(payload);
		ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class); // 클라이언트로부터 메시지를 받아 채팅 메시지 객체로 변환
		ChatRoom room = chatService.findRoomById(chatMessage.getRoomId()); // 전달받은 메시지 안에 담긴 채팅방id로 채팅방 정보를 조
		room.handleActions(session, chatMessage, chatService); // 해당 채팅방에 입장해 있는 모든 클라이언트들에게 메시지 타입에 따른 메시지 전송
	}
}
