package com.wayakeji.common.security.config;

import com.wayakeji.common.security.Interceptor.AuthorityInterceptor;
import com.wayakeji.common.security.resolver.MultiRequestBodyArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebAppConfigurer implements WebMvcConfigurer {

	private final Configurations configuration;

	private final AuthorityInterceptor authorityInterceptor;

	private final MultiRequestBodyArgumentResolver multiRequestBodyArgumentResolver;

	private static final List<String> EXCLUDE_PATH= Arrays.asList("/","/css/**","/js/**","/img/**","/media/**","/vendors/**");
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/");
	}
	
	/**
	 * 
	 * 添加自定义拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		List<HandlerInterceptor> interceptor = configuration.getInterceptors();
//		if(interceptor.size() > 0) {
//			for (HandlerInterceptor handlerInterceptor : interceptor) {
//				registry.addInterceptor(handlerInterceptor)
//						.addPathPatterns("/**") // 用于添加拦截规则 addPathPatterns("/**") //所有路径都被拦截
//						.excludePathPatterns(EXCLUDE_PATH); //excludePathPatterns 用户排除拦截
//			}
//		}
		registry.addInterceptor(authorityInterceptor)
				.addPathPatterns("/**") // 用于添加拦截规则 addPathPatterns("/**") //所有路径都被拦截
				.excludePathPatterns(EXCLUDE_PATH); //excludePathPatterns 用户排除拦截
	}

	/**
     * 注册自定义参数解析器
     * @return
     */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		 // 添加MultiRequestBody参数解析器
//		List<HandlerMethodArgumentResolver> resolversList =  configuration.getResolvers();
//		if(resolversList.size() > 0) {
//			for (HandlerMethodArgumentResolver handlerMethodArgumentResolver : resolversList) {
//				resolvers.add(0,handlerMethodArgumentResolver);
//			}
//		}
		resolvers.add(0, multiRequestBodyArgumentResolver);
	}

	
	/**
	 * registry.addInterceptor(getInterfaceAuthCheckInterceptor()).addPathPatterns("/api/**");
	 * 这种方式无论什么情况都可以 registry.addInterceptor(new
	 * InterfaceAuthCheckInterceptor()).addPathPatterns("/api/**");这种情况时，
	 * 自定义的interceptor中不能注入其他内容，比如redis或者其他service，如果要注入，必须使用上面这种方法
	 */



}
