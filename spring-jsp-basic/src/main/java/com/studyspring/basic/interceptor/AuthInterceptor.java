package com.studyspring.basic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.studyspring.basic.member.MemberDTO;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if(session != null) {
			MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
			if(memberDTO != null) {
				System.out.println("세션 member 있음, 통과");
				return true;
			}
		}
		response.sendRedirect(request.getContextPath() + "/login");
		System.out.println("세션 member 없음, 통과X, 리다이렉트 " + request.getContextPath() + "/login");
		return false;
	}
	
}
