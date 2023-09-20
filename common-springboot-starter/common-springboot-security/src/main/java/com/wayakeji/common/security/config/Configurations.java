package com.wayakeji.common.security.config;


import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.util.Charset;
import com.wayakeji.common.core.util.SpringContextHolder;
import com.wayakeji.common.security.util.signer.Signer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@Configuration
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "configuration")
public class Configurations {

	private final SpringContextHolder springContextHolder;
	
	public static String charset = Charset.UTF_8;
	
	/**
	 * 验证签名的签名器列表
	 */
	private List<Signer> verifySigner;
	/**
	 * 配置参数解析器
	 */
//	private List<HandlerMethodArgumentResolver> resolvers;
	/**
	 * 配置拦截器
	 */
//	private List<HandlerInterceptor> interceptors;

	private String name;

	/**
	 * 设置签名类
	 * 
	 * @return
	 */
	public void setVerifySigner(List<String> verifySigner) {
		if(verifySigner.size() > 0) {
			List<Signer> list = new ArrayList<>();
			for (String str : verifySigner) {
				try {
					@SuppressWarnings("static-access")
					Signer sig = (Signer) springContextHolder.getBean(str,Signer.class);
					list.add(sig);
				} catch (Exception e) {
					log.error("配置签名类错误",e);
					throw new HandyserveException("配置签名类错误");
				}
			}
			this.verifySigner = list;
		}
	}

//	public void setResolvers(List<String> resolvers) {
//		if(resolvers.size() > 0) {
//			List<HandlerMethodArgumentResolver> list = new ArrayList<>();
//			for (String str : resolvers) {
//				try {
//					@SuppressWarnings("static-access")
//					HandlerMethodArgumentResolver resolv = (HandlerMethodArgumentResolver) springContextHolder.getBean(str,HandlerMethodArgumentResolver.class);
//					list.add(resolv);
//				} catch (Exception e) {
//					log.error("配置参数解析器错误",e);
//					throw new HandyserveException("配置参数解析器错误");
//				}
//			}
//			this.resolvers = list;
//		}
//	}
//
//	public void setInterceptors(List<String> interceptors) {
//		if(interceptors.size() > 0) {
//			List<HandlerInterceptor> list = new ArrayList<>();
//			for (String str : interceptors) {
//				try {
//					@SuppressWarnings("static-access")
//					HandlerInterceptor interce = (HandlerInterceptor) springContextHolder.getBean(str,HandlerInterceptor.class);
//					list.add(interce);
//				} catch (Exception e) {
//					log.error("配置拦截器错误",e);
//					throw new HandyserveException("配置拦截器错误");
//				}
//			}
//			this.interceptors = list;
//		}
//	}





}