package com.jeecms.common.util;

/**
 * 有关字符操作的工具类
 * @author swc 2018-03-14
 *
 */
public class StringUtils {

	/**
	 * 判断字符是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}

}
