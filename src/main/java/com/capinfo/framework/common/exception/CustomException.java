package com.capinfo.framework.common.exception;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CustomException(String message){
		super(message);
		this.message = message;
	}

	//异常信息
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
