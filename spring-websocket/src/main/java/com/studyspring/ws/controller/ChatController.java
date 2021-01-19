package com.studyspring.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyspring.ws.websocket.dto.ChatRoom;
import com.studyspring.ws.websocket.repository.ChatRepository;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private ChatRepository chatRepository;

	// 모든 채팅방 목록 반환
	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room() {
		return chatRepository.findAllRoom();
	}
 // 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
 	public ChatRoom createRoom(@RequestParam String name) {
	 return chatRepository.createChatRoom(name);
 	}
 // 특정 채팅방 조회
 	@GetMapping("/room/{roomId}")
 	@ResponseBody
 	public ChatRoom roomInfo(@PathVariable String roomId) {
 		return chatRepository.findRoomById(roomId);
 	}
}