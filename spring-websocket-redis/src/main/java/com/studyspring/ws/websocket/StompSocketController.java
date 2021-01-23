package com.studyspring.ws.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.studyspring.ws.websocket.dto.ChatMessage;

//publish 요청 prefix로 들어오는 요청을 MessageMapping으로 잡아 처리한다.
@Controller
public class StompSocketController {
	@Autowired
	private RedisTemplate<String, ChatMessage> chatRedisTemplate;
	
	@MessageMapping("/chattingPub")
	public void chatting(ChatMessage message) { //필드변수명과 이름이 일치하면 알아서 객체에 담아준다.
		// 클라이언트가 chattingPub으로 publish요청한 메시지를 redis의 test channel로 publish 요청한다
		chatRedisTemplate.convertAndSend("test", message);
	}
}
