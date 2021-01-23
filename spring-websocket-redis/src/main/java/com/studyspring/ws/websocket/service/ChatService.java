package com.studyspring.ws.websocket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import com.studyspring.ws.redis.RedisChatMessageSubscriber;
import com.studyspring.ws.websocket.dto.ChatRoom;
import com.studyspring.ws.websocket.repository.ChatRepository;

@Service
public class ChatService {
	private ChatRepository chatRepository;
	
	@Autowired
	public ChatService(ChatRepository chatRepository) {
		this.chatRepository = chatRepository;
	}
	
	public Optional<ChatRoom> enterRoom(String id) {
		ChatRoom chatRoom = chatRepository.findRoomById(id);
		return Optional.ofNullable(chatRoom);
	}
	public List<ChatRoom> findAllRoom() {
		return chatRepository.getAllRoom();
	}
	public ChatRoom createChatRoom(String name) {
		return chatRepository.createChatRoom(name);
	}
	public ChatRoom findRoomById(String id) {
		return chatRepository.findRoomById(id);
	}
}
