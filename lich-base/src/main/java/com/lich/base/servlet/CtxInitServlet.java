package com.lich.base.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.lich.common.constants.ConfigsKeyCons;
import com.lich.common.util.PropertiesUtil;
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

	public void initCtx(HttpServletRequest request, ServletConfig config) {
		System.out.println("===================初始化ContextPath===================");
		System.out.println("ctx-path:" + request.getContextPath());
		request.getSession().setAttribute("ctx", request.getContextPath());
		// 另一个服务器应用路径
		String app_server_path = PropertiesUtil.getValue(ConfigsKeyCons.APP_SERVERS_PATH);
		request.getSession().setAttribute("app_server_path", app_server_path);
		
	}
	
}
