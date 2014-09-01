package com.lich.common.util.web;

import org.springframework.ui.ModelMap;

import com.lich.common.constants.MessageCons;

/**
 * 页面提示工具类
 * @author Lich
 * 2014年8月25日 上午10:40:25
 */
public class PageTipsUtil {

	/**
	 * 错误类型 </br>
	 * 页面接收信息为<code>${message}</code>，结果为"error" </br>
	 * 返回页面的提示信息 </br>
	 * 页面接收信息为<code>${tips}</code>，结果为tips的内容
	 * @param modelMap
	 */
	public static void ERROR(ModelMap modelMap, String tips) {
		modelMap.put(MessageCons.MSG, MessageCons.ERROR);
		TIPS(modelMap, tips);
	}
	/**
	 * 成功类型 </br>
	 * 页面接收信息为<code>${message}</code>，结果为"success" </br>
	 * * 返回页面的提示信息 </br>
	 * 页面接收信息为<code>${tips}</code>，结果为tips的内容
	 * @param modelMap
	 */
	public static void SUCCESS(ModelMap modelMap, String tips) {
		modelMap.put(MessageCons.MSG, MessageCons.SUCCESS);
		TIPS(modelMap, tips);
	}
	/**
	 * 返回页面的提示信息 </br>
	 * 页面接收信息为<code>${tips}</code>，结果为tips的内容
	 * @param modelMap
	 * @param tips
	 */
	public static void TIPS(ModelMap modelMap, String tips) {
		modelMap.put(MessageCons.TIPS, tips);
	}
	
}
