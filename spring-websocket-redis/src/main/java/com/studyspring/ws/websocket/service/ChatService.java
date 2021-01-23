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
    private RedisChatMessageSubscriber redisChatMessageSubscriber;
	private RedisMessageListenerContainer redisContainer;
	
	@Autowired
	public ChatService(RedisChatMessageSubscriber redisChatMessageSubscriber, RedisMessageListenerContainer redisMessageListenerContainer, ChatRepository chatRepository) {
		this.redisChatMessageSubscriber = redisChatMessageSubscriber;
		this.redisContainer = redisMessageListenerContainer;
		this.chatRepository = chatRepository;
	}
	
	public Optional<ChatRoom> enterRoom(String id) {
		// 클라이언트가 방에 입장했을 때 서버가 해당 토픽에 대해 redis에 구독상태가 아니라면 구독을 해줘야한다.
		ChatRoom chatRoom = chatRepository.findRoomById(id);
		if(chatRoom != null) { //방이 존재한다면
			
			//서버가 해당 방id에 대해 redis 구독상태인지 검사
			ChannelTopic channelTopic = chatRepository.getRedisTopicChannel(id);
			//생성된 채널이 없다면 추가 후 서버에서 redis 구독해 해당 채널로 메시지 전송받도록 함
			if(channelTopic == null) {
				channelTopic = chatRepository.addRedisTopicChannel(id);
				
				// 해당 redis채널토픽(방id)로 들어오는 메시지는 redisChatMessageSubscriber에서 처리하겠다.
				redisContainer.addMessageListener(redisChatMessageSubscriber, channelTopic);
			}
		}
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
