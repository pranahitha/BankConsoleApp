package com.revature.training.pms.exceptions;

public class InvalidException extends RuntimeException {
	public InvalidException()
	{
		
	}
	public InvalidException(String msg) {
		super(msg);
	}
}
