package com.studyspring.basic.exception;

public class AuthenticationException extends RuntimeException {
	
	public AuthenticationException() {
		super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
	}
	public AuthenticationException(Exception e) {
		super(e);
	}

}
