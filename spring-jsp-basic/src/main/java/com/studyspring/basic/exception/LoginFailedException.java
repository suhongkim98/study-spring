package com.studyspring.basic.exception;

public class LoginFailedException extends RuntimeException {
	public LoginFailedException() {
		super(ErrorCode.LOGIN_FAILED.getMessage());
	}
	public LoginFailedException(String e) {
		super(e);
	}
}
