package com.lich.base.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
/**
 * 初始化ContextPath
 * @author Lich
 * 2014年7月21日 下午4:00:06
 */
public class CtxInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void initCtx(HttpServletRequest request) {
		System.out.println("===================初始化ContextPath===================");
		System.out.println("ctx-path:" + request.getContextPath());
		request.getSession().setAttribute("ctx", request.getContextPath());
	}
	
}
