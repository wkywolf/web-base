package com.lich.common.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * <p>Gson的使用 <p>
 * 使用Gson的版本：gson-2.2.4.jar <br>
 * json转换器 <br>
 * 
 * @Date 2014年6月27日 上午11:29:28
 * @author Lich
 * @version v1.1
 *
 */
public class GsonUtil {
	
	private static Gson gson = null;
	
	static {
		if(gson == null) {
//			gson = new Gson();
			gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // gson解析和生成带时间格式的数据
		}
	}
	/**
	 * 防止实例化Gson工具类对象
	 */
	private GsonUtil() {
		
	}
	/**
	 * <p>对象转换为json格式字符串<p> 
	 * 1. 参数为实体对象，返回的形式为：{"A":"a", "B":"b", ……} <br>
	 * 2. 参数为数组对象，返回的形式为：{"a", "b", "c", ……} <br>
	 * 3. 参数为List对象，返回的形式为：[{"A":"a", "B":"b", ……}, {"A":"a", "B":"b", ……}, ……] <br>
	 * @param obj
	 * @return String jsonStr
	 */
	public static String convertToString(Object obj) {
		String jsonStr = null;
		if(gson != null){
			jsonStr = gson.toJson(obj);
		}
		return jsonStr;
	}
	/**
	 * json格式字符串转换成javaBean对象
	 * @param jsonStr
	 * @param classOfT
	 * @return
	 */
	public static <T> T convertToBean(String jsonStr, Class<T> classOfT) {
		T t = null;
		if(gson != null){
			t = (T) gson.fromJson(jsonStr, classOfT);
		}
		return t;
	}
	/**
	 * json格式字符串转换为List对象
	 * @param jsonStr
	 * @return List
	 */
	public static <T> List<T> convertToList(String jsonStr) {
		List<T> list = null;
		if(gson != null){
			Type typeOfT = new TypeToken<List<T>>() {}.getType();
			list = convertToList(jsonStr, typeOfT);
		}
		return list;
	} 
	/**
	 * json格式字符串转换为List对象，指定类型
	 * @param jsonStr
	 * @param typeOfT
	 * @return List
	 */
	public static <T> List<T> convertToList(String jsonStr, Type typeOfT) {
		List<T> list = null;
		if(gson != null){
			list = gson.fromJson(jsonStr, typeOfT);
		}
		return list;
	}
	/**
	 * json格式字符串转换为Map对象
	 * @param jsonStr
	 * @return Map
	 */
	public static <T, N> Map<T, N> convertToMap(String jsonStr) {
		Map<T, N> map = null;
		if(gson != null){
			Type typeOfT = new TypeToken<Map<T, N>>() {}.getType();
			map = gson.fromJson(jsonStr, typeOfT);
		}
		return map;
	}
	/**
	 * 获取json格式字符串中属性为 key 的值
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getJsonValue(String jsonStr, String key) {
		T t = null;
		if(gson != null){
			Map<?, ?> map = convertToMap(jsonStr);
			if(map!=null && map.size()>0){
				if(key!=null && !"".equals(key)){
					t = (T) map.get(key);
				}
			}
		}
		return t;
	}
	
}
