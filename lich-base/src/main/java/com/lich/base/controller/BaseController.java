package com.lich.base.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.lich.base.model.User;
import com.lich.base.service.BaseService;

/**
 * 父Controller，提供共用方法
 * @author Lich
 * 2014年8月19日 下午5:01:18
 */
@Controller
public class BaseController {

	@Autowired
	protected BaseService<Serializable> baseService;
	
	public User getCurrUser(HttpServletRequest req) {
		return (User) getSession(req).getAttribute("currUser");
	}
	
	public HttpSession getSession(HttpServletRequest req) {
		return req.getSession();
	}
	
}
