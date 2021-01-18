package com.studyspring.ws.websocket.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

public class ChatRoom {
	//채팅방이다. 세션정보들하고 방번호, 이름을 가지고 있음
	private String roomId;
	private String name;
	private Set<WebSocketSession> sessions;
	
	public ChatRoom() {
		sessions = new HashSet<>();
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
	public Set<WebSocketSession> getSessions() {
		return sessions;
	}
	public void setSessions(Set<WebSocketSession> sessions) {
		this.sessions = sessions;
	}
	
}
