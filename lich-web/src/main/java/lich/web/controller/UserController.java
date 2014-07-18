package lich.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户Controller
 * @author Lich
 * 2014年7月18日 下午5:48:06
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/addUser", method=RequestMethod.GET)
	public String addUser(ModelMap modelMap) {
		
		return "user/user_add";
	}
	
	
}
