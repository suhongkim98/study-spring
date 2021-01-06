package com.studyspring.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AuthenticationException.class)
	protected ModelAndView handleAuthenticationException(AuthenticationException e) {
		ModelAndView mav = new ModelAndView();
		
		ErrorResponse errorResponse 
		= ErrorResponse.create()
		.code(ErrorCode.AUTHENTICATION_FAILED.getCode())
		.message(e.getMessage())
		.status(ErrorCode.AUTHENTICATION_FAILED.getStatus());
		
		mav.setViewName("/");
		mav.addObject("exception", errorResponse);
		return mav;
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(LoginFailedException.class)
	protected ModelAndView handleLoginFailedException(LoginFailedException e) {
		ModelAndView mav = new ModelAndView();
		
		ErrorResponse errorResponse
		= ErrorResponse.create()
		.code(ErrorCode.LOGIN_FAILED.getCode())
		.message(e.getMessage())
		.status(ErrorCode.LOGIN_FAILED.getStatus());
		
		mav.setViewName("/login");
		mav.addObject("exception", errorResponse);
		return mav;
	}
}
