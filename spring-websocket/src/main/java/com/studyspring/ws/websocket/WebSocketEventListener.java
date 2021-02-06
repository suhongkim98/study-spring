package com.studyspring.ws.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class WebSocketEventListener {
	//웹소켓 connect, disconnect, subs, unsubs 이벤트 관리
	@EventListener
	private void handleSessionConnected(SessionConnectEvent event) {
		System.out.println("연결");
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		System.out.println(accessor.getDestination());
	}
	@EventListener
	private void handleSessionDisconnected(SessionDisconnectEvent event) {
		System.out.println("연결 종료");
	}
	@EventListener
	private void handleSessionSubscribed(SessionSubscribeEvent event) {
		System.out.println("구독");
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
		System.out.println(accessor.getDestination());
	}
	@EventListener
	private void handleSessionUnsubscribed(SessionUnsubscribeEvent event) {
		System.out.println("구독 해제");
		SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
	}
}