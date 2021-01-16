package com.studyspring.basic.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.basic.member.MemberDTO;
import com.studyspring.basic.provider.dto.BoardDTO;
import com.studyspring.basic.provider.service.BoardService;
import com.studyspring.basic.provider.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private BoardService boardService;
	
	@GetMapping
	public ModelAndView course(HttpSession session, @RequestParam("courseIdx") int courseIdx) {
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> boards = boardService.selectBoard(courseIdx).orElseGet(() -> null);
		mav.setViewName("course");
		mav.addObject("member",member);
		mav.addObject("boards", boards);
		return mav;
	}
	@GetMapping("/view")
	public ModelAndView boardView(HttpSession session, @RequestParam("boardIdx") int boardIdx) {
		ModelAndView mav = new ModelAndView();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		mav.addObject("member",member);
		//boardIdx 로 게시판 정보, 댓글 가져와 속성추가하기
		mav.setViewName("boardview");
		return mav;
	}
	@GetMapping("/testdb")
	public ModelAndView testdb() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		
		courseService.registerCourse("test", 6);
		return mav;
	}
}
