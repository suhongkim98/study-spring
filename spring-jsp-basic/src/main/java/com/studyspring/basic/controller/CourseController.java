package com.studyspring.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.studyspring.basic.controller.dto.WriteRequestDTO;
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
		List<BoardDTO> boards = boardService.selectBoardAll(courseIdx).orElseGet(() -> null);
		mav.setViewName("course");
		mav.addObject("member",member);
		mav.addObject("boards", boards);
		mav.addObject("courseIdx", courseIdx);
		return mav;
	}
	@GetMapping("/view")
	public ModelAndView boardView(HttpSession session, @RequestParam("boardIdx") int boardIdx) {
		ModelAndView mav = new ModelAndView();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		mav.setViewName("boardview");
		mav.addObject("member",member);
		//boardIdx 로 게시판 정보, 댓글 가져와 속성추가하기
		BoardDTO board = boardService.selectBoard(boardIdx).orElseGet(() -> null);
		mav.addObject("board", board);
		return mav;
	}
	@GetMapping("/write")
	public ModelAndView writeBoard(@RequestParam("courseIdx") int courseIdx) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("write");
		mav.addObject("courseIdx", courseIdx);
		return mav;
	}
	@PostMapping("/write/writeRequest")
	public ModelAndView writeRequest(HttpServletRequest request,WriteRequestDTO writeRequestDTO) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberDTO member = (MemberDTO) session.getAttribute("member");
		
		//보드서비스 작업
		BoardDTO board = new BoardDTO();
		board.setMember(member);
		board.setTitle(writeRequestDTO.getTitle());
		board.setContent(writeRequestDTO.getContent());
		board.setCourseIdx(writeRequestDTO.getCourseIdx());
		boardService.registerBoard(board);
		
		mav.setViewName("redirect:/course?courseIdx=" + writeRequestDTO.getCourseIdx()); //이전페이지로 이동
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
