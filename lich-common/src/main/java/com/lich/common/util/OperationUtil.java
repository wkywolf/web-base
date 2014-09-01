package com.lich.common.util;

import com.lich.common.constants.ActionCons;
import com.lich.common.constants.emummap.ActionMap;

/**
 * 日志内容
 * @author Lich
 * 2014年8月25日 下午4:55:19
 */
public class OperationUtil {

	/**
	 * 组合操作记录内容
	 * @param action 操作的动作
	 * @param operator 操作者
	 * @param optedObjId 被操作者ID
	 * @param optedObj 被操作者
	 * @param servletPath 执行动作的程序路径
	 * @return 操作记录内容
	 */
	public static String getOperation(String action, String operator, String optedObjId, String optedObj, String servletPath) {
		String operation = "";
		if(ActionCons.ADD.equals(action) || ActionCons.EDIT.equals(action) || ActionCons.DEL.equals(action)) {
			operation = "[" + operator +"] " + ActionMap.getValue(action) + " [" + servletPath + "]" + "id为[" + optedObjId + "] / 名称为[" + optedObj + "]的记录操作";
		}else if(ActionCons.LIST.equals(action)) {
			operation = "[" + operator +"]获取列表数据 [" + servletPath + "]";
		}else if(ActionCons.LOGIN.equals(action)){
			operation = "[" + operator +"] 登录  [" + servletPath + "] 操作";
		}else if(ActionCons.LOGOUT.equals(action)){
			operation = "[" + operator +"] 登出  [" + servletPath + "] 操作";
		}else {
			operation = "[" + operator +"] 进行未知操作 [" + servletPath + "] " + "id为[" + optedObjId + "] / 名称为[" + optedObj + "]的记录操作";
		}
		return operation;
	}
	
}
