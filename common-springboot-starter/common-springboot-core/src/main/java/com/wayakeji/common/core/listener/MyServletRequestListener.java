package com.wayakeji.common.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;


@Component
public class MyServletRequestListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(MyServletRequestListener.class);
    
    /**
     * request在接口之前监听
     */
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
    	// 将所有request请求都携带上httpSession
		((HttpServletRequest) servletRequestEvent.getServletRequest()).getSession();

    }

    /**
     * 接口之后监听
     */
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        logger.info("request end");
		
    }

}