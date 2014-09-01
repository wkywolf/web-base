package com.lich.common.util.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 字符串直接输出到页面工具类
 * @author Lich
 * 2014年8月13日 上午10:29:07
 */
public class OutPutPageUtil {

	public static void write(HttpServletResponse response, String data) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			out.write(data.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out != null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}