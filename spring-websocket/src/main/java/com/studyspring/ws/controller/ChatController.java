package com.studyspring.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyspring.ws.websocket.dto.ChatRoom;
import com.studyspring.ws.websocket.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@PostMapping
	public ChatRoom createRoom(@RequestParam("name") String name) { //POST로 이름을 받아오면 해당 이름을 가진 방 생성 후 반환
		return chatService.createRoom(name);
	}
	@GetMapping
	public List<ChatRoom> findAllRoom() { // 모든 방 리스트를 찾아 반환한다.
		return chatService.findAllRoom();
	}
}
