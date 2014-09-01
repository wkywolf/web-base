package com.lich.common.constants;

import com.lich.common.util.PropertiesUtil;

/**
 * 路径常量类
 * @author Lich
 * 2014年8月27日 上午10:38:45
 */
public class PathCons {
	/**
	 * 用户图片相对路径
	 */
	public static final String USER_IMG_PATH = "/images/user/profile";
	/**
	 * 宠物图片相对路径
	 */
	public static final String PET_IMG_PATH = "/images/pet/profile";
	/**
	 * 用户默认头像路径
	 */
	public static final String DEF_USER_PATH = USER_IMG_PATH + "/default.png";
	/**
	 * 宠物默认头像路径
	 */
	public static final String DEF_PET_PATH = PET_IMG_PATH + "/default.png";
	/**
	 * 上传图片的服务器路径
	 */
	public static final String SERVERS_UPLOAD_PATH = PropertiesUtil.getValue(ConfigsKeyCons.APP_SERVERS_UPLOAD_PATH);
	/**
	 * 上传户图片路径
	 */
	public static final String USER_IMG_UPLOAD_PATH = SERVERS_UPLOAD_PATH + "/images/user/profile";
	/**
	 * 上传宠物图片路径
	 */
	public static final String PET_IMG_UPLOAD_PATH = SERVERS_UPLOAD_PATH + "/images/pet/profile";
	
	
	
}
