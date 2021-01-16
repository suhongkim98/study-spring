package com.studyspring.basic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.basic.member.MemberDTO;
import com.studyspring.basic.provider.dto.CourseDTO;
import com.studyspring.basic.provider.service.CourseService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/")
	public ModelAndView home(HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		List<CourseDTO> courses = courseService.selectAll().orElseGet(() -> null);
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("home");
		mav.addObject("member",member);
		mav.addObject("courses", courses);
		return mav;
	}
}
