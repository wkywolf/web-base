package com.lich.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 设置默认值工具类
 * @author Lich
 * 2014年8月19日 下午3:48:19
 */
public class DefaultValUtil {
	/**
	 * Object类型默认值
	 * @param value
	 * @param defVal
	 * @return
	 */
	public static Object defVal(Object value, Object defVal) {
		if(value==null || "".equals(value)) return defVal;
		else return value;
	}
	/**
	 * String类型默认值
	 * @param value
	 * @param defVal
	 * @return
	 */
	public static String defVal(String value, String defVal) {
		return  StringUtils.isBlank(value) ? defVal : value;
	}
	/**
	 * Double类型默认值
	 * @param value
	 * @param defVal
	 * @return
	 */
	public static Double defVal(Double value, Double defVal) {
		return value==null ? defVal : value;
	}
	/**
	 * Long类型默认值
	 * @param value
	 * @param defVal
	 * @return
	 */
	public static Long defVal(Long value, Long defVal) {
		return value==null ? defVal : value;
	}
	/**
	 * Integer类型默认值
	 * @param value
	 * @param defVal
	 * @return
	 */
	public static Integer defVal(Integer value, Integer defVal) {
		return value==null ? defVal : value;
	}
	
}
