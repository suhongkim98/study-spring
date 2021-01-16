package com.studyspring.basic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.basic.controller.dto.LoginRequestDTO;
import com.studyspring.basic.controller.dto.RegisterRequestDTO;
import com.studyspring.basic.core.Role;
import com.studyspring.basic.member.MemberDTO;
import com.studyspring.basic.member.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public ModelAndView loginPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if(memberDTO != null) {
			//이미 세션이 존재한다
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("login");
		}
		return mav;
	}
	@GetMapping("/register")
	public ModelAndView registerPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if(memberDTO != null) {
			//이미 세션이 존재한다
			mav.setViewName("redirect:/");
		} else {
			mav.setViewName("register");
		}
		return mav;
	}
	@PostMapping("/loginRequest")
	public ModelAndView loginRequest(HttpSession session, LoginRequestDTO loginRequestDTO) {
		ModelAndView mav = new ModelAndView();
		MemberDTO member = memberService.loginMember(loginRequestDTO).orElseGet(() -> null);
		if(member != null) {
			session.setAttribute("member", member);
			mav.addObject("member", member);
		}
		mav.setViewName("redirect:/");
		return mav;
	}
	@GetMapping("/logoutRequest")
	public ModelAndView logoutRequest(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.invalidate(); // 로그아웃
		mav.setViewName("redirect:/");
		return mav;
	}
	
	@PostMapping("/registerRequest")
	public ModelAndView registerRequest(HttpSession session, RegisterRequestDTO registerRequestDTO) {
		ModelAndView mav = new ModelAndView();
		memberService.registerMember(registerRequestDTO);
		mav.setViewName("redirect:/");
		return mav;
	}
}
