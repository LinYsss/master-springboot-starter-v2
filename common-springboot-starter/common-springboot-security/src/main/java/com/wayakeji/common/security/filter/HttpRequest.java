package com.wayakeji.common.security.filter;

import java.lang.reflect.Method;

/**
 * Http请求对象接口
 * 
 * @author hu trace
 *
 */
public interface HttpRequest{

	String id();

	/**
	 * 获取请求url
	 * 
	 * @return
	 */
	String url();

	/**
	 * 获取请求文本内容
	 * 
	 * @return
	 */
	String body();

	/**
	 * 获取请求连接信息
	 * 
	 * @return
	 */
	String socketAddr();

	/**
	 * 传入true调用{@link #ip(boolean)}
	 * 
	 * @see #ip(boolean)
	 * @return
	 */
	String ip();

	/**
	 * 获取请求方的ip
	 * <p>
	 * 根据agency判断，true则优先获取消息头，false则直接取连接ip。
	 * <p>
	 * 此方法预留在此，如有特殊情况还是自己手动获取较好。
	 * 
	 * @param agency true则优先获取消息头，false则直接取连接ip。
	 * @return
	 */
	String ip(boolean agency);

	/**
	 * 获取请求对应的映射类
	 *
	 * @return
	 */
	Class<?> control();

	/**
	 * 获取请求对应的映射方法
	 *
	 * @return
	 */
	Method mapping();
}
