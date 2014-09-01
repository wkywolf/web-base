package com.lich.common.util.web;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

import com.lich.common.constants.SearchDefineCons;
import com.lich.common.util.DefaultValUtil;

/**
 * 封装客户端参数工具类
 * @author Lich
 * 2014年8月13日 下午2:59:00
 */
public class RequestParamterUtil {
	/**
	 * 返回String类型的值，可设置默认值
	 * @param req
	 * @param key
	 * @param stringVal 可传String类型参数，设置默认值
	 * @return
	 */
	public static String getStringParam(HttpServletRequest req, String key, String... stringVal) {
		String value = StringUtils.trim(req.getParameter(key));
		return (String) getDefVal(value, stringVal);
	}
	/**
	 * 返回Integer类型的值，可设置默认值
	 * @param req
	 * @param key 客户端参数名
	 * @param intVal 可传Integer类型参数，设置默认值
	 * @return
	 */
	public static Integer getIntegerParam(HttpServletRequest req, String key, Integer... intVal) {
		String valueStr = StringUtils.trim(req.getParameter(key));
		Integer value = null;
		if(valueStr!=null && !"".equals(valueStr)){
			value = Integer.valueOf(valueStr);
		}
		return (Integer) getDefVal(value, intVal);
	}
	/**
	 * 返回Long类型的值，可设置默认值
	 * @param req
	 * @param key 客户端参数名
	 * @param longVal 可传Long类型参数，设置默认值
	 * @return
	 */
	public static Long getLongParam(HttpServletRequest req, String key, Long... longVal) {
		String valueStr = StringUtils.trim(req.getParameter(key));
		Long value = null;
		if(valueStr!=null && !"".equals(valueStr)){
			value = Long.valueOf(valueStr);
		}
		return (Long) getDefVal(value, longVal);
	}
	/**
	 * 默认值
	 * @param source
	 * @param objects
	 * @return
	 */
	private static Object getDefVal(Object source, Object... objects) {
		for(Object defVal : objects) {
			source = DefaultValUtil.defVal(source, defVal);
		}
		return source;
	}
	
	/**
	 * 获取页面参数
	 * @param searchParamMap
	 * 			参数名 </br>
	 * 			命名规范：search_ + 实体对应的属性名，如实体User类里有属性userName：search_userName
	 * @param modelMap
	 * 			
	 * @return
	 */
	public static Map<String, Object> getSearchParam(Map<String, Object> searchParamMap, ModelMap modelMap) {
		Map<String, Object> realSearchParams = new TreeMap<String, Object>();
		for(Map.Entry<String, Object> entry : searchParamMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value==null){
				continue;
			}
			if(value instanceof String && StringUtils.isBlank((String) value)){
				continue;
			}
			key = StringUtils.trim(key);
			value = StringUtils.trim(value.toString());
			realSearchParams.put(key, value);
			modelMap.put(SearchDefineCons.SEARCH_PREFIX + key, value);
		}
		return realSearchParams;
	}
	
}
