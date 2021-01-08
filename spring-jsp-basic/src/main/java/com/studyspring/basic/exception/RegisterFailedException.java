package com.studyspring.basic.exception;

public class RegisterFailedException extends RuntimeException {
	public RegisterFailedException() {
		super(ErrorCode.REGISTER_FAILED.getMessage());
	}
	public RegisterFailedException(String message) {
		super(message);
	}

}
