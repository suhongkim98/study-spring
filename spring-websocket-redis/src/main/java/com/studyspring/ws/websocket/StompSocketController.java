package com.studyspring.ws.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.studyspring.ws.websocket.dto.ChatMessage;

//publish 요청 prefix로 들어오는 요청을 MessageMapping으로 잡아 처리한다.
@Controller
public class StompSocketController {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@MessageMapping("/testPub") //testPub로 들어오는 메시지 매핑
	public void testWebsocket(ChatMessage message) { //필드변수명과 이름이 일치하면 알아서 객체에 담아준다.
		messagingTemplate.convertAndSend("/topic/test", message); // test를 구독하는 구독자들에게 message 전송
	}
	
	@MessageMapping("/chattingPub")
	public void chatting(ChatMessage message) {
		messagingTemplate.convertAndSend("/topic/" + message.getRoomId(), message); // 특정 방에 구독한 클라이언트들에게 메시지 전송
	}
}
