package com.wayakeji.common.security.Interceptor;

import com.wayakeji.common.core.constant.ErrorConstants;
import com.wayakeji.common.core.exception.HandyserveException;
import com.wayakeji.common.security.annotation.Authority;
import com.wayakeji.common.security.config.Configurations;
import com.wayakeji.common.security.filter.HttpServerRequest;
import com.wayakeji.common.security.login.*;
import com.wayakeji.common.security.util.signer.Signer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 权限拦截器
 * 
 * @author
 *
 */
@Component
@RequiredArgsConstructor
public class AuthorityInterceptor implements HandlerInterceptor, Ordered {

	private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);

	/**
	 * 设置接收的用户id标识符（QueryString参数名称）
	 * <p>
	 * 该值只在权限拦截器中使用，拦截器会将此参数转换为clientId，以便使用
	 */
	private static String qsParamId = "clientId";

	public static String getQsParamId() {
		return qsParamId;
	}

	private final Configurations configuration;

	private final  Buffers buffers;

	/**
	 * 处理请求之前执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpServerRequest httpServerRequest = null;
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}else if(request instanceof HttpServerRequest) {
			httpServerRequest = (HttpServerRequest) request;
		}else {
			httpServerRequest = new HttpServerRequest(request);
//			httpServerRequest = (HttpServerRequest) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		setHttpServerRequest(httpServerRequest,handler);
		httpServerRequest.ip();
		Authority authority = authority(httpServerRequest, handler);
		if (authority != null) {
			request1(httpServerRequest, response);
		}
		return true;
	}
	
	
	private void setHttpServerRequest(HttpServerRequest request, Object handler) {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		request.setControl(handlerMethod.getBeanType());
		request.setMapping(method);
	}

	/**
	 * 获取当前请求接口对应的方法/类上的{@link Authority}注解
	 * 
	 * @param request
	 * @return
	 */
	private Authority authority(HttpServerRequest request, Object handler) {
		Authority authority = request.mapping().getAnnotation(Authority.class);
		if (authority != null) {
			return authority;
		}
		authority = request.control().getAnnotation(Authority.class);
		return authority;
	}

	/**
	 * 开始执行拦截验证
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	private void request1(HttpServerRequest request, HttpServletResponse response) throws Exception {
		String url = url(request);
		long clientId = clientId(request);
		String token = request.getHeader(AuthorityConstants.HEADER_TOKEN);
		BufferUser buf = buffer(clientId, token);
		checkSign(request, buf);
		checkAuthority(clientId, url, request.getMethod());
		bufMergeParams(request, buf.getCustom());
	}

	/**
	 * 获取url地址
	 * 
	 * @param request
	 * @return
	 */
	private String url(HttpServerRequest request) {
		String project = configuration.getName();
		String url = request.url();
		if (url.length() >= project.length()) {
			if (url.substring(0, project.length()).equals(project)) {
				url = url.substring(project.length());
			}
		}
		return url;
	}

	/**
	 * 获取clientId
	 * 
	 * @param request
	 * @return
	 * @throws HandyserveException
	 */
	private long clientId(HttpServletRequest request) throws HandyserveException {
		String cid = request.getParameter(qsParamId);
		if (cid == null) {
			throw new HandyserveException(ErrorConstants.HEADER_ERROR, "你必须通过QueryString传入。" + qsParamId);
		}
		request.setAttribute("clientId", cid);
		try {
			return Long.parseLong(cid);
		} catch (Exception e) {
			throw new HandyserveException(ErrorConstants.HEADER_ERROR, "格式错误，它应该是数字类型。" + cid);
		}
	}

	/**
	 * 获取buffer并验证token
	 * 
	 * @param clientId
	 * @param token
	 * @return
	 * @throws HandyserveException
	 */
	private BufferUser buffer(Long clientId, String token) throws HandyserveException {
		if (token == null || token.isEmpty()) {
			throw new HandyserveException(ErrorConstants.HEADER_ERROR,
					"token不存在，你需要添加消息头" + AuthorityConstants.HEADER_TOKEN);
		}
		return buffers.checkToken(clientId, token);
	}

	/**
	 * 合并buffer中的数据值当前请求{@link HttpServerRequest}中
	 * 
	 * @param request
	 * @param buf
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void bufMergeParams(HttpServerRequest request, Map<String, Object> buf) {
		if (buf != null) {
			Object obj;
			for (Entry<String, Object> entry : buf.entrySet()) {
				obj = entry.getValue();
				if (obj != null) {
					if (obj instanceof Map) {
						bufMergeParams(request, (Map) obj);
					} else {
						request.setAttribute(entry.getKey(), obj.toString());
					}
				}
			}
		}
	}

	/**
	 * 验证签名
	 * <p>
	 * 验证签名根据{@link  }设置的顺序判断，如果有重复的contentType，则第一个生效。
	 * 
	 * @param request
	 * @param buf
	 * @throws Exception
	 */
	private void checkSign(HttpServerRequest request, BufferUser buf) throws Exception {
		// 如果是get请求，则不验证签名
		if (!request.getMethod().equals(HttpMethod.GET.name())) {
			String sign = request.getHeader(AuthorityConstants.HEADER_SIGN);
			if (sign == null) {
				throw new HandyserveException(ErrorConstants.HEADER_ERROR,
						"签名不存在，你需要添加消息头: " + AuthorityConstants.HEADER_SIGN);
			}
			String ct = request.getContentType().split(";")[0].trim();
			for (Signer signer : configuration.getVerifySigner()) {
				if (signer.contentType().equals(ct)) {
					String sign1 = signer.sign(request, buf);
					log.debug("授权请求签名信息: " + sign +"------"+ sign1);
					if (sign.equals(sign1)) {
						return;
					}
					throw new HandyserveException(ErrorConstants.HEADER_ERROR, "验证签名失败，请检查签名是否正确。");
				}
			}
			throw new HandyserveException(ErrorConstants.HEADER_ERROR, "没有找到对应的签名器：" + ct);
		}
	}

	/**
	 * 验证权限
	 * 
	 * @param id
	 * @param api
	 * @param method
	 * @throws Exception
	 */
	private void checkAuthority(long id, String api, String method) throws HandyserveException {
		int[] mids = CacheApis.get(api, method);
		if (mids.length == 0) {
			log.error("没有操作权限: {} ---> {}", api, method);
			throw new HandyserveException("没有操作权限");
		}
		BufferAuthority ba = buffers.getAuthority(id);
		if (!ba.hasId(mids)) {
			log.error("没有操作权限: {} ---> {}", api, method);
			throw new HandyserveException("没有操作权限");
		}
	}

	@Override
	public int getOrder() {
		return 1;
	}
}
