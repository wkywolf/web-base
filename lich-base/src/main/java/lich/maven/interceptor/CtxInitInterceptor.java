package lich.maven.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CtxInitInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		System.out.println("===================初始化ContextPath===================");
//		System.out.println("ctx-path:" + request.getContextPath());
//		request.setAttribute("ctx", request.getContextPath());
		return true;
	}
	
}
