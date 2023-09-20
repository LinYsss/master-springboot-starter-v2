package com.wayakeji.common.security.filter;

import com.wayakeji.common.core.constant.CommonConstants;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = {"/*"},filterName = "channelFilter")
public class ChannelFilter implements Filter ,Ordered{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(request instanceof HttpServletRequest) {
            HttpServerRequest requestWrapper = new HttpServerRequest((HttpServletRequest) request);
            requestWrapper.addHeader(CommonConstants.REQUEST_START_TIME, String.valueOf(System.currentTimeMillis()));
            chain.doFilter(requestWrapper, response);
        }else{
            chain.doFilter(request, response);
        }
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
