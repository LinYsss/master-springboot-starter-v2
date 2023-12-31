package com.wayakeji.common.core.util.qs.annotation;

import java.lang.annotation.*;

/**
 * <p>在JavaBean与QueryStringObject互相转换时使用
 * <p>在JavaBean的字段上添加此注解，可以指定字段的别名
 * @author hutrace
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QSField {
	
	/**
	 * JavaBean与QS对象互转的别名
	 * @return 别名
	 */
	String name();
	
}
