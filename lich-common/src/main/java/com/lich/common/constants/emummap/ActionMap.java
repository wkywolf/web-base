package com.lich.common.constants.emummap;
/**
 * 动作工具类映射类 </br>
 * 与ActionCons类结合使用
 * @author Lich
 * 2014年8月25日 下午5:01:57
 */
public enum ActionMap {
	
	LIST			("list", "列表"),
	ADD				("add", "新增"),
	EDIT			("edit", "编辑"),
	DEL				("del", "删除"),
	LOGIN			("login", "登录"),
	LOGOUT			("logout", "登出");
	
	private final String key;
	
	private final String value;

	private ActionMap(String key, String value) {
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
		for (ActionMap mapper : ActionMap.values()) {
			if (mapper.getValue().equals(value)) {
				return mapper.getKey();
			}
		}
		return null;
	}

	public static final String getValue(String key) {
		for (ActionMap mapper : ActionMap.values()) {
			if (mapper.getKey().equals(key)) {
				return mapper.getValue();
			}
		}
		return null;
	}

}

