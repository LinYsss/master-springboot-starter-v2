package com.wayakeji.common.core.util.qs.parse;


import com.wayakeji.common.core.util.qs.ParseConfig;
import com.wayakeji.common.core.util.qs.QueryStringObject;

import java.util.Map;

/**
 * <p>map对象解析器
 * <p>实现将Map转换为QueryString对象
 * @author hutrace
 */
public class MapParser extends AbstractParser {
	
	public static final MapParser instance = new MapParser();
	
	private MapParser() {}

	@SuppressWarnings("unchecked")
	public QueryStringObject parse(Object object, ParseConfig[] config) {
		Map<Object, Object> map = (Map<Object, Object>) object;
		QueryStringObject qs = new QueryStringObject(map.size(), config);
		qs.putAll(map);
		return qs;
	}
	
}
