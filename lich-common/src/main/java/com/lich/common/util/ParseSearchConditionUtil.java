package com.lich.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lich.common.constants.SQLKeyWordCons;
import com.lich.common.constants.emummap.SQLKeyWordMap;

/**
 * 把Map参数封装为HQL的条件工具类
 * @author Lich
 * 2014年8月15日 下午12:35:29
 */
public class ParseSearchConditionUtil {
	
	private static final Logger log = LoggerFactory.getLogger(ParseSearchConditionUtil.class);

	/**
	 * 实现对搜索条件的搜索属性进行解析并组合成HQL条件查询语句
	 * @param conditionMap
	 * @return
	 */
	public static String parseHQLCondition(Map<String, Object> conditionMap) {
		if(conditionMap==null || conditionMap.isEmpty()){
			log.error("Map conditionMap为空...");
			return "";
		}
		StringBuffer conditionSB = new StringBuffer();
		Iterator<Entry<String, Object>> it = conditionMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Object> entry = (Entry<String, Object>) it.next();
			String key = entry.getKey();
			String value = (String) entry.getValue();
			/**
			 * 这里实现对搜索条件的搜索属性进行解析：
			 * 如：eq_searchProp1, cn_searchProp2, ... 。解析为[eq searchProp1] / [cn searchProp2] / ... 
			 * 解析完后，再组装成SQL的查询条件语句
			 */
			String[] parseKey = key.split("_");
			String sqlKeyWord = parseKey[0]; // 解析出SQL的关键字，如：eq, cn, lt, ...
			String sqlProp = parseKey[1]; // 解析出HQL查询条件的属性（字段），如：id, name, ...
			/*
			 * 组装查询条件
			 */
			if(sqlKeyWord.contains(SQLKeyWordCons.DEQ)){
				conditionSB.append(" and " + sqlProp + " " + SQLKeyWordMap.getValue(sqlKeyWord) + " DATE_FORMAT('" + value + "', '%Y-%m-%d %H:%i:%s')");
			}else if(sqlKeyWord.contains(SQLKeyWordCons.DGTEQ)){
				conditionSB.append(" and " + sqlProp + " " + SQLKeyWordMap.getValue(sqlKeyWord) + " DATE_FORMAT('" + value + "', '%Y-%m-%d %H:%i:%s')");
			}else if(sqlKeyWord.contains(SQLKeyWordCons.DLTEQ)){
				conditionSB.append(" and " + sqlProp + " " + SQLKeyWordMap.getValue(sqlKeyWord) + " DATE_FORMAT('" + value + "', '%Y-%m-%d %H:%i:%s')");
			}else if(sqlKeyWord.contains(SQLKeyWordCons.CN)){
				conditionSB.append(" and " + sqlProp + " " + SQLKeyWordMap.getValue(sqlKeyWord) + " " + "'%" + value + "%'");
			}else{
				conditionSB.append(" and " + sqlProp + " " + SQLKeyWordMap.getValue(sqlKeyWord) + " '" + value + "'");
			}
		}
		return conditionSB.toString();
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cn_name", "lich11");
		map.put("eq_id", "1001");
		map.put("eq_password", "pwd_abc");
		map.put("gteq_number", "1");
		map.put("lteq_number", "10");
		map.put("dlteq_sd", "2014-08-17 00:00:00");
		map.put("dgteq_sd", "2014-08-07 00:00:00");
		String result = parseHQLCondition(map);
		System.out.println(result);
	}
	
}
