package lich.web.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 初始化ContextPath
 * @author Lich
 * 2014年7月21日 下午4:00:06
 */
public class CtxInitServlet implements Servlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("===================初始化ContextPath===================");
		String ctx = config.getServletContext().getContextPath();
		System.out.println("ctx-path:" + ctx);
		config.getServletContext().setAttribute("ctx", ctx);
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
