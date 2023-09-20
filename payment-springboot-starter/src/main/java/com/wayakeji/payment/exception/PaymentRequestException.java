package com.wayakeji.payment.exception;

/**
 * <p>请求中的异常
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @see Exception
 * @since 1.8
 * @version 1.0
 * @time 2019年7月3日
 */
public class PaymentRequestException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public PaymentRequestException(String msg) {
		super(msg);
	}
	
	public PaymentRequestException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
