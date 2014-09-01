package com.lich.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 * 
 * @Date 2014年5月29日 下午2:34:48
 * @author Lich
 *
 */
public class MD5Util {
	
	public static void main(String[] args) {
		String sourceStr = "123456";
		String result = MD5(sourceStr);
		System.out.println("MD5(" + sourceStr + ",32) = " + result);
	}
	/**
	 * 生成32位MD5码
	 * @param sourceStr
	 * @return 返回大写的MD5码
	 */
	public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("UTF-8")); // 对源编码为UTF-8
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        return result;
    }
	
}
