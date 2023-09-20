package com.wayakeji.common.core.exception;

/**
 * 根据名字理解，此异常时针对文件冲突时抛出的
 * @author hu trace
 *
 */
public class FileConflictException extends AppLoaderException {

	private static final long serialVersionUID = 1L;

	public FileConflictException(String message) {
		super(message);
	}
	
	public FileConflictException(String message, Throwable e) {
		super(message, e);
	}
	
}
