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
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.ws.websocket.dto.ChatRoom;
import com.studyspring.ws.websocket.service.ChatService;

@Controller
@RequestMapping("/chatting")
public class ChatController {
	@Autowired
	private ChatService chatService;
	
	@GetMapping
	public ModelAndView chattingPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chat");
		return mav;
	}
	@GetMapping("/enter/{roomId}")
	public ModelAndView enterRoom(@PathVariable String roomId) {
		//채팅방 입장
		ModelAndView mav = new ModelAndView();
		ChatRoom chatRoom = chatService.enterRoom(roomId).orElseGet(() -> null);
		if(chatRoom != null) {
			mav.addObject("chatRoom", chatRoom);
			mav.setViewName("chattingRoom");
		} else {
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	// 모든 채팅방 목록 반환
	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room() {
		return chatService.findAllRoom();
	}
 // 채팅방 생성
	@PostMapping("/createRoom")
	@ResponseBody
 	public ChatRoom createRoom(@RequestParam String name) {
	 return chatService.createChatRoom(name);
 	}
 // 특정 채팅방 조회
 	@GetMapping("/room/{roomId}")
 	@ResponseBody
 	public ChatRoom roomInfo(@PathVariable String roomId) {
 		return chatService.findRoomById(roomId);
 	}
}