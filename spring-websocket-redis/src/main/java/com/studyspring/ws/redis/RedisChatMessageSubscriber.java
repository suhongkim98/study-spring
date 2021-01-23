package com.studyspring.ws.redis;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyspring.ws.websocket.dto.ChatMessage;

@Service
public class RedisChatMessageSubscriber implements MessageListener {
	//redis에서 오는 메시지를 stomp구독자들에게 뿌리는 객체이다.
	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	private ObjectMapper mapper = new ObjectMapper();
	@Override
	public void onMessage(Message message, byte[] pattern) {
		// TODO Auto-generated method stub
		try {
			ChatMessage chatMessage = mapper.readValue(message.getBody(), ChatMessage.class); // json을 객체로
			messagingTemplate.convertAndSend("/topic/" +chatMessage.getRoomId(), chatMessage); // redis subs에서 받은 메시지를 stomp에서 해당 방을 구독하고 있는 구독자들에게 메시지 전송한다.
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
