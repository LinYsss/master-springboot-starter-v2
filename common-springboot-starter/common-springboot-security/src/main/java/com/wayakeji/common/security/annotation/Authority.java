package com.wayakeji.common.security.annotation;

import java.lang.annotation.*;

/**
 * 验证权限注解
 * <p>在需要验证权限的接口方法上添加此注解
 * <p>即可将接口方法加入权限验证
 * @author hu trace
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {

}