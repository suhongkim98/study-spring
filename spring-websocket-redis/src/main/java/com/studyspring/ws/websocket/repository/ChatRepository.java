package com.studyspring.ws.websocket.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;

import com.studyspring.ws.websocket.dto.ChatRoom;

@Repository
public class ChatRepository {
//채팅방 생성, 조회, 관리
	private Map<String, ChatRoom> chatRoomMap; // 채팅방 관리 map
	private Map<String, ChannelTopic> redisChannelTopicMap; //현재 서버가 구독하고 있는 redis채널 목록
	
	public ChatRepository() {
		chatRoomMap = new LinkedHashMap<>();
		redisChannelTopicMap = new LinkedHashMap<>();
	}
	
	public List<ChatRoom> getAllRoom() {
		List<ChatRoom> chatRooms = new ArrayList<ChatRoom>(chatRoomMap.values());
		Collections.reverse(chatRooms);// 뒤집기 
		return chatRooms;
	}
	
	public ChatRoom findRoomById(String id) {
		return chatRoomMap.get(id);
	}
	
	public ChannelTopic getRedisTopicChannel(String id) {
		return redisChannelTopicMap.get(id);
	}
	
	public ChannelTopic addRedisTopicChannel(String id) {
		ChannelTopic channelTopic = new ChannelTopic(id);
		redisChannelTopicMap.put(id, channelTopic);
		return channelTopic;
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
