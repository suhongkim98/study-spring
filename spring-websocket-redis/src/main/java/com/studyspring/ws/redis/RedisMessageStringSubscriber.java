package com.studyspring.ws.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageStringSubscriber implements MessageListener {
//예제
	@Override
	public void onMessage(Message message, byte[] pattern) {
		System.out.println(message.toString());
	}
	
	
}
