package com.wayakeji.common.core.util.qs.parse;

import com.wayakeji.common.core.exception.QueryStringException;
import com.wayakeji.common.core.util.qs.ParseConfig;
import com.wayakeji.common.core.util.qs.Parser;
import com.wayakeji.common.core.util.qs.QueryStringObject;

import java.util.Comparator;

/**
 * <p>qs解析器抽象类
 * <p>继承者需要实现的解析过程
 * <p>抽象类对解析结果做配置属性的写入
 * @author hutrace
 */
public abstract class AbstractParser implements Parser {
	
	public QueryStringObject parse(Object object, ParseConfig[] config, String charset,
								   Comparator<? super String> sort) throws QueryStringException {
		return parse(object, config).setConfig(charset, sort);
	}
	
	/**
	 * 解析接口，需要继承者实现
	 * @param object 需要解析的对象
	 * @return QS对象
	 * @throws QueryStringException
	 */
	protected abstract QueryStringObject parse(Object object, ParseConfig[] config) throws QueryStringException;

	@Override
	public <T> T transform(Class<T> clazs) {
		return null;
	}
	
}
