package com.wayakeji.common.security.handler;

import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public
	R<Void> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e,
			HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',不支持'{}'请求", requestURI, e.getMethod());
		return R.failed(e.getMessage());
	}

	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	public R<Void> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',发生未知异常.", requestURI, e);
		return R.failed(e.getMessage());
	}

	/**
	 * 系统异常
	 */
	@ExceptionHandler(Exception.class)
	public R<Void> handleException(Exception e, HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.error("请求地址'{}',发生系统异常.", requestURI, e);
		return R.failed(e.getMessage());
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(BindException.class)
	public R<Void> handleBindException(BindException e) {
		log.error(e.getMessage(), e);
		String message = e.getAllErrors().get(0).getDefaultMessage();
		return R.failed(message);
	}

	/**
	 * 自定义验证异常
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public R<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error(e.getMessage(), e);
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		return R.failed(message);
	}

	/**
	 * 处理自定义的业务异常
	 */
	@ExceptionHandler(value = HandyserveException.class)
	public R<Void> handHandyserveException(HandyserveException e) {
		log.error(e.getMessage(), e);
		String message = e.getMessage();
		return R.failed(message);
	}

	/**
	 * 上面异常处理器不包含时的保底处理
	 *
	 * @param e
	 * @return
	 * @create bojan 2021/4/23 9:24
	 */
	@ExceptionHandler(NoHandlerFoundException.class)
	public R<Void> handNoHandlerFoundException(NoHandlerFoundException e) {
		log.error(e.getMessage(), e);
		String message = e.getMessage();
		return R.failed(message);
	}
}
