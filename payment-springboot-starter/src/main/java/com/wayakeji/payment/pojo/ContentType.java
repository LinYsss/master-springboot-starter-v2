package com.wayakeji.payment.pojo;

/**
 * <p>请求参数类型, 通过此值程序会自动将map类型的参数进行转换
 * @author <a href="http://www.wayakeji.net/"> Waya Co.,Ltd Hutrace</a>
 * @since 1.8
 * @version 1.0
 * @time 2019年7月23日
 */
public enum ContentType {
	
	/**
	 * XML格式参数
	 * <p>程序会自动将map或json参数转换成xml格式
	 */
	XML,
	/**
	 * JSON格式参数
	 * <p>程序会自动将map参数转换成json格式
	 */
	JSON,
	/**
	 * queryString格式参数
	 * <p>程序会自动将map参数转换成queryString格式
	 */
	QUERY_STRING
	
}
