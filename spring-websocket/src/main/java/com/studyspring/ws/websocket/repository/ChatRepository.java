package com.studyspring.ws.websocket.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.studyspring.ws.websocket.dto.ChatRoom;

@Repository
public class ChatRepository {
//채팅방 생성, 조회, 관리
	private Map<String, ChatRoom> chatRoomMap;
	
	public ChatRepository() {
		chatRoomMap = new LinkedHashMap<>();
	}
	public List<ChatRoom> findAllRoom() {
		List<ChatRoom> chatRooms = new ArrayList<ChatRoom>(chatRoomMap.values());
		Collections.reverse(chatRooms);// 뒤집기 
		return chatRooms;
	}
	public ChatRoom findRoomById(String id) {
		return chatRoomMap.get(id);
	}
	public ChatRoom createChatRoom(String name) {
		String randomId = UUID.randomUUID().toString();
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setName(name);
		chatRoom.setRoomId(randomId);
		chatRoomMap.put(randomId, chatRoom);
		return chatRoom;
	}
}
