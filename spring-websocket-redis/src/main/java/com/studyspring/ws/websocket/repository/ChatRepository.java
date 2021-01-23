package com.studyspring.ws.websocket.repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Repository;

import com.studyspring.ws.websocket.dto.ChatRoom;

@Repository
public class ChatRepository {
//채팅방 생성, 조회, 관리
	private HashOperations<String, String, ChatRoom> opsChatRooms;// 채팅방을 redis에 저장
	private Map<String, ChannelTopic> redisChannelTopicMap; //현재 서버가 구독하고 있는 redis채널 목록
	
	private static final String CHAT_ROOMS = "CHAT_ROOM";
	@Autowired
	public ChatRepository(RedisTemplate<String, ChatRoom> chatRoomRedisTemplate) {
		redisChannelTopicMap = new LinkedHashMap<>();
		opsChatRooms = chatRoomRedisTemplate.opsForHash(); // redis template 주입받아서 생성
	}
	public ChatRepository() {
	}
	
	public List<ChatRoom> getAllRoom() {
		return opsChatRooms.values(CHAT_ROOMS);
	}
	
	public ChatRoom findRoomById(String id) {
		return opsChatRooms.get(CHAT_ROOMS, id);
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
		opsChatRooms.put(CHAT_ROOMS, randomId, chatRoom);
		return chatRoom;
	}
}
