package com.studyspring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.basic.controller.dto.RegisterRequestDTO;
import com.studyspring.basic.exception.LoginFailedException;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	@GetMapping("/register")
	public ModelAndView registerPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}
	@PostMapping("/loginRequest")
	public ModelAndView loginRequest() {
		ModelAndView mav = new ModelAndView();
		throw new LoginFailedException();
		//return mav;
	}
	@PostMapping("/registerRequest")
	public ModelAndView registerRequest(RegisterRequestDTO registerRequestDTO) {
		ModelAndView mav = new ModelAndView();
		//service, dao 작업
		
		//
		mav.setViewName("redirect:/");
		return mav;
	}
}
