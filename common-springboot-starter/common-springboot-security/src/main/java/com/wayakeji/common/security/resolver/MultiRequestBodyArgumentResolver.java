//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.wayakeji.common.security.resolver;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.core.util.ServeUtil;
import com.wayakeji.common.core.util.code.AES;
import com.wayakeji.common.core.util.xml.XMLSerializer;
import com.wayakeji.common.security.Interceptor.AuthorityInterceptor;
import com.wayakeji.common.security.annotation.MultiRequestBody;
import com.wayakeji.common.security.config.Configurations;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.BufferUser;
import com.wayakeji.common.security.login.Buffers;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

@Component
public class MultiRequestBodyArgumentResolver implements HandlerMethodArgumentResolver, Ordered {

	private @Autowired Buffers buffers;

	public MultiRequestBodyArgumentResolver() {
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(MultiRequestBody.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServerRequest servletRequest = (HttpServerRequest)webRequest.getNativeRequest(HttpServerRequest.class);
		JSONObject jsonObject = null;
		String contentType = servletRequest.getContentType();
		String body = null;
		String parameter1 = servletRequest.getParameter(AuthorityInterceptor.getQsParamId());
		Long clientId = null;
		BufferUser bu = null;
		if (!ObjectUtils.isEmpty(parameter1)) {
			clientId = Long.parseLong(parameter1);
			bu = buffers.getUser(clientId);
			if (!ObjectUtils.isEmpty(bu)) {
				servletRequest.addParameters(bu.getCustom());
			}
		}

		if ("application/scpsat-json".equals(contentType)) {
			body = AES.dncode(bu.getSecret(), servletRequest.getBody(), Configurations.charset);
		} else {
			Map map;
			if ("application/xml".equals(contentType)) {
				map = XMLSerializer.parse(servletRequest.getBody());
				body = JSON.toJSONString(map);
			} else if ("text/xml".equals(contentType)) {
				map = XMLSerializer.parse(servletRequest.getBody());
				body = JSON.toJSONString(map);
			} else {
				body = servletRequest.getBody();
			}
		}

		if (!ObjectUtils.isEmpty(body)) {
			jsonObject = JSON.parseObject(body);
			if (clientId != null) {
				if (!ObjectUtils.isEmpty(bu)) {
					jsonObject.putAll(bu.getCustom());
				}

				jsonObject.put("clientId", clientId);
			}
		} else {
			Map<String, Object> map = new HashMap();
			Enumeration<String> parameterKet = servletRequest.getParameterNames();

			while(parameterKet.hasMoreElements()) {
				String key = (String)parameterKet.nextElement();
				map.put(key, servletRequest.getParameter(key));
			}

			jsonObject = JSON.parseObject(JSON.toJSONString(map));
		}

		MultiRequestBody parameterAnnotation = (MultiRequestBody)parameter.getParameterAnnotation(MultiRequestBody.class);
		String key = parameterAnnotation.value();
		Object value;
		if (ObjectUtil.isNotEmpty(key)) {
			value = servletRequest.getParameter(key);
			if (value == null && parameterAnnotation.required()) {
				throw new IllegalArgumentException(String.format("required param %s is not present", key));
			}
		} else {
			key = parameter.getParameterName();
			value = servletRequest.getParameter(key) == null ? jsonObject.get(key) : servletRequest.getParameter(key);
		}

		Class<?> parameterType = parameter.getParameterType();
		if (value != null) {
			return ServeUtil.simpleType(parameterType) ? TypeUtils.castToJavaBean(value, parameterType) : JSON.parseObject(value.toString(), parameterType);
		} else if (this.isBasicDataTypes(parameterType)) {
			if (parameterAnnotation.required()) {
				throw new IllegalArgumentException(String.format("required param %s is not present", key));
			} else {
				return null;
			}
		} else if (!parameterAnnotation.parseAllFields()) {
			if (parameterAnnotation.required()) {
				throw new IllegalArgumentException(String.format("required param %s is not present", key));
			} else {
				return null;
			}
		} else {
			Object result;
			try {
				result = JSON.parseObject(jsonObject.toString(), parameterType);
				if (result != null) {
					this.validateIfApplicable(parameter, mavContainer, webRequest, binderFactory, result);
				}
			} catch (JSONException var23) {
				result = null;
				throw new HandyserveException("类型转换不匹配", var23);
			}

			if (!parameterAnnotation.required()) {
				return result;
			} else {
				boolean haveValue = false;
				Field[] declaredFields = parameterType.getDeclaredFields();
				Field[] var19 = declaredFields;
				int var20 = declaredFields.length;

				for(int var21 = 0; var21 < var20; ++var21) {
					Field field = var19[var21];
					field.setAccessible(true);
					if (field.get(result) != null) {
						haveValue = true;
						break;
					}
				}

				if (!haveValue) {
					throw new IllegalArgumentException(String.format("required param %s is not present", key));
				} else {
					return result;
				}
			}
		}
	}

	private void validateIfApplicable(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer, NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory, Object arg) throws Exception {
		parameter = parameter.nestedIfOptional();
		String name = Conventions.getVariableNameForParameter(parameter);
		if (binderFactory != null) {
			WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
			if (arg != null) {
				this.validateIfApplicable(binder, parameter);
				if (binder.getBindingResult().hasErrors() && this.isBindExceptionRequired(binder, parameter)) {
					throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
				}
			}
		}

	}

	protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
		int i = parameter.getParameterIndex();
		Class<?>[] paramTypes = parameter.getExecutable().getParameterTypes();
		boolean hasBindingResult = paramTypes.length > i + 1 && Errors.class.isAssignableFrom(paramTypes[i + 1]);
		return !hasBindingResult;
	}

	private void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		Annotation[] var4 = annotations;
		int var5 = annotations.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			Annotation ann = var4[var6];
			Validated validatedAnn = (Validated)AnnotationUtils.getAnnotation(ann, Validated.class);
			if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann);
				Object[] validationHints = hints instanceof Object[] ? (Object[])((Object[])hints) : new Object[]{hints};
				binder.validate(validationHints);
				break;
			}
		}

	}

	private boolean isBasicDataTypes(Class<?> clazz) {
		Set<Class<?>> classSet = new HashSet();
		classSet.add(Integer.class);
		classSet.add(Long.class);
		classSet.add(Short.class);
		classSet.add(Float.class);
		classSet.add(Double.class);
		classSet.add(Boolean.class);
		classSet.add(Byte.class);
		classSet.add(Character.class);
		return classSet.contains(clazz);
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
