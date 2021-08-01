package com.revature.training.pms.exceptions;

public class InsufficientAmountException extends RuntimeException {

	public InsufficientAmountException()
	{
		
	}
	public InsufficientAmountException(String msg)
	{
		super(msg);
	}
	
}
