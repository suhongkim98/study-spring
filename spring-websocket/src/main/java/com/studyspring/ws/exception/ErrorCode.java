package com.studyspring.ws.exception;


public enum ErrorCode {
	
	AUTHENTICATION_FAILED(401, "AUTH_001", " AUTHENTICATION_FAILED.");
	
	private final String code;
	private final String message;
	private final int status;
	
	ErrorCode(final int status, final String code, final String message){
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}
	
}