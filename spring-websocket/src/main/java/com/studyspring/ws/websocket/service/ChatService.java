package com.studyspring.ws.websocket.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspring.ws.websocket.dto.ChatRoom;

@Service
public class ChatService {
	private final ObjectMapper objectMapper;
	private Map<String, ChatRoom> chatRooms;
	
	public ChatService() {
		objectMapper = new ObjectMapper();
		chatRooms = new LinkedHashMap<String, ChatRoom>();
	}
	
	public List<ChatRoom> findAllRoom(){
		//방들을 반환한다.
		return new ArrayList<>(chatRooms.values());
	}
	public ChatRoom findRoomById(String roomId) {
		return chatRooms.get(roomId);
	}
	public ChatRoom createRoom(String name) {
		//방을 생성하고 채팅방map에 집어넣는다.
		String randomId = UUID.randomUUID().toString();
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setRoomId(randomId);
		chatRoom.setName(name);
		chatRooms.put(randomId, chatRoom);
		return chatRoom;
	}
	public <T> void sendMessage(WebSocketSession session, T message) {
		try {
			session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
		} catch(IOException e) {
			System.out.println(e);
		}
	}
}
