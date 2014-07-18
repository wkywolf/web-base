package lich.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第一个Controller </br>
 * 测试
 * @author Lich
 * 2014年7月17日 下午5:03:26
 */
@Controller
@RequestMapping("/firstController")
public class FirstController {

	private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
	
	@RequestMapping(value="/first", method=RequestMethod.GET)
	public String first(@RequestParam(value="value") String value, HttpServletRequest req, HttpServletResponse res, ModelMap modelMap) {
		modelMap.put("value", value);
		logger.info("值：" + value);
		return "first/first";
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
    public String user(HttpServletRequest request, Model model) {
		model.addAttribute("value", "234444");
        return "first/first";
    }
			
}
