package com.lich.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件上传/删除/下载工具类
 * @author Lich
 * 2014年8月29日 上午11:06:43
 */
public class FileUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 文件文件复制(上传)
	 * @param source
	 * @param destination
	 */
	public static void copyFile(InputStream source, File destination) {
		try {
			FileUtils.copyInputStreamToFile(source, destination);
		} catch (IOException e) {
			logger.error(e.getMessage(), " 文件复制(上传)失败");
			e.printStackTrace();
		}
	}
	/**
	 * 文件/目录删除 如果参数为目录 </br>
	 * 1. 如果参数为目录,则把目录及目录下的所有文件删除
	 * 2. 如果为文件，则删除该文件
	 * @param deletedFile
	 * @return true/false
	 */
	public static boolean deleteFile(File deletedFile) {
		return FileUtils.deleteQuietly(deletedFile);
	}
	
}
