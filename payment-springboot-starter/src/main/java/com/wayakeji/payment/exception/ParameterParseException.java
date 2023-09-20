package com.wayakeji.payment.exception;

public class ParameterParseException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ParameterParseException() {
		super();
	}
	
	public ParameterParseException(String msg) {
		super(msg);
	}
	
	public ParameterParseException(Throwable e) {
		super(e);
	}
	
	public ParameterParseException(String msg, Throwable e) {
		super(msg, e);
	}
	
}
