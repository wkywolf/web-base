package com.lich.common.util;

import java.io.IOException;
import java.util.Properties;
/**
 * 读取properties文件工具类
 * @author Lich
 * 2014年8月27日 上午10:03:12
 */
public class PropertiesUtil {
	
	private static Properties props = new Properties();

	/** 
     * 读取properties配置文件信息 
     */
	static {
		try {
			props.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** 
     * 根据key取值
     */
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	public static void main(String[] args) {
		String val = PropertiesUtil.getValue("app.servers.path");
		System.out.println(val);
	}
	
}
