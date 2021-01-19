package com.studyspring.ws.websocket.dto;

public class ChatRoom {
	//채팅방이다. 세션정보들하고 방번호, 이름을 가지고 있음
	private String roomId;
	private String name;
	
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
