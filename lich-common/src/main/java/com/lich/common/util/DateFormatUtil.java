package com.lich.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具类
 * @author Lich
 * 2014年7月11日 下午9:58:56
 */
public class DateFormatUtil {
	/**
	 * 日期类型格式化为字符类型
	 * @param date
	 * 			日期类型<code>Date</code>
	 * @param format
	 * 			日期转换的格式： </br>
	 * 			格式字符串组合：yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String getDateToStr(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	/**
	 * 日期类型格式化为字符类型
	 * @param dateStr
	 * 			需要转换的字符类型
	 * @param format
	 * 			日期转换的格式： </br>
	 * 			格式字符串组合：yyyy-MM-dd HH:mm:ss
	 * @return <code>Date</code>
	 */
	public static Date getStrToDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(dateStr);
	}
	
	public static void main(String[] args) throws ParseException {
		String str = getDateToStr(new Date(), "yyyy-MM-dd HH:mm");
		System.out.println(str);
		Date date = getStrToDate(str, "yyyy-MM-dd HH:mm");
		System.out.println(date);
		
	}
	
}
