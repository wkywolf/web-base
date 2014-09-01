package lich.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lich.web.model.User;
import lich.web.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lich.base.controller.BaseController;

/**
 * 用户Controller
 * @author Lich
 * 2014年7月18日 下午5:48:06
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(HttpServletRequest req, ModelMap modelMap) {
		List<User> users = userService.findUsers();
		modelMap.put("users", users);
		logger.info("成功获取列表……");
		logger.error("错误信息......");
		logger.debug("debug......");
		return "user/user_list";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(ModelMap modelMap) {
		modelMap.put("user", new User());
		return "user/user_add";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(User user, ModelMap modelMap) {
		try{
			userService.saveOrUpdateUser(user);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage() + "新增或更新失败...");
		}
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(@RequestParam Long id, ModelMap modelMap) {
		try{
			User user = null;
			if(id != null){
				user = userService.findUserById(id);
			}else{
				user = new User();
			}
			modelMap.put("user", user);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage() + "获取ID＝" + id + "的用户信息失败...");
		}
		return "user/user_add";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam Long id) {
		try{
			userService.delete(id);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage() + "删除失败...");
		}
		return "redirect:/user/list";
	}
	
}
