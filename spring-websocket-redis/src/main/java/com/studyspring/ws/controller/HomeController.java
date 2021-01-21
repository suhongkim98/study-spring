package com.studyspring.ws.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	@GetMapping("/test")
	public String testWebsocket() {
		//이 페이지는 웹소켓-stomp 개념을 공부하기 위한 테스트 페이지입니다.
		return "test";
	}
	
}
