package com.studyspring.ws.websocket.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.studyspring.ws.websocket.service.ChatService;

public class ChatRoom {
	//채팅방이다. 세션정보들하고 방번호, 이름을 가지고 있음
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions;
	
	public ChatRoom() {
		sessions = new HashSet<>();
	}
	public void handleActions(WebSocketSession session, ChatMessage chatMessage, ChatService chatService) {
		if(chatMessage.getType().equals(ChatMessage.MessageType.ENTER)) {
			sessions.add(session);
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다");
		}
		sendMessage(chatMessage, chatService);
	}
	public <T> void sendMessage(T message, ChatService chatService) {
		sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
