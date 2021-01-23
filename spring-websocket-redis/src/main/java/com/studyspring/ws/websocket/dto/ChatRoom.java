package com.studyspring.ws.websocket.dto;

import java.io.Serializable;

public class ChatRoom implements Serializable{
	private static final long serialVersionUID = 6494678977089006639L; // redis에 저장되는 객체들은 serialize가능해야하므로 serializable을 참조하도록 선언
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
