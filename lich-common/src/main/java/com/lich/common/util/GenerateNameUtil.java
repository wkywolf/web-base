package com.lich.common.util;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

/**
 * 生成名字工具类
 * @author Lich
 * 2014年8月27日 下午12:29:07
 */
public class GenerateNameUtil {
	
	/**
	 * 随机产生一个UUID随机64位序列(用户的文件名)
	 * @param suffix 文件后缀名，如果为空则只返回名称
	 * @return
	 */
	public static String randomNameByUUID(String suffix){
		UUID uuid = UUID.randomUUID();
		String name = uuid.toString().replaceAll("-", "");
		if(StringUtils.isBlank(suffix)){
			return name;
		}
		return name + suffix;
	}
	/**
	 * 产生一个精确到三位毫秒的名称(用户的文件名)
	 * @param suffix 文件后缀名，如果为空则只返回名称
	 * @return
	 */
	public static String getNameByDateStr(String suffix){
		String name = DateFormatUtil.getDateToStr(new Date(), "yyyyMMddHHmmssSSS");
		if(StringUtils.isBlank(suffix)){
			return name;
		}
		return name + suffix;
	}
	
}
