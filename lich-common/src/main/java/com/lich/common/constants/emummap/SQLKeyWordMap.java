package com.lich.common.constants.emummap;
/**
 * SQL关键字常量映射类 </br>
 * 与SQLKeyWordCons类结合使用
 * @author Lich
 * 2014年8月15日 下午5:01:57
 */
public enum SQLKeyWordMap {
	
	EQ					("eq", "="),
	LT					("lt", "<"),
	GT					("gt", ">"),
	LTEQ				("lteq", "<="),
	GTEQ				("gteq", ">="),
	CN					("cn", "like"),

	DEQ					("deq", "="),
	DLTEQ				("dlteq", "<="),
	DGTEQ				("dgteq", ">=");
	
	private final String key;
	
	private final String value;

	private SQLKeyWordMap(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public static final String getKey(String value) {
		for (SQLKeyWordMap mapper : SQLKeyWordMap.values()) {
			if (mapper.getValue().equals(value)) {
				return mapper.getKey();
			}
		}
		return null;
	}

	public static final String getValue(String key) {
		for (SQLKeyWordMap mapper : SQLKeyWordMap.values()) {
			if (mapper.getKey().equals(key)) {
				return mapper.getValue();
			}
		}
		return null;
	}

}

