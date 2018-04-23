package com.jeecms.common.util;

import java.sql.Timestamp;
import java.util.Date;

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

	/**
	 * 字符串转为Integer型
	 * @param str
	 * @return
	 */
	public static Integer strToInteger(String str) {
	    if (isEmpty(str)) {
	        return null;
	    }
	    return Integer.valueOf(str);
	}

	public static String toString(Object obj) {

        if (obj == null) {
            return "";
        }
        if (obj.getClass().getName().equals("java.lang.String")) {
            return toString((String) obj);
        }
        if (obj.getClass().getName().equals("java.lang.Integer")) {
            return toString((Integer) obj);
        }
        if (obj.getClass().getName().equals("java.lang.Long")) {
            return toString((Long) obj);
        }
        if (obj.getClass().getName().equals("java.sql.Date")) {
            return toString((Date) obj);
        }
        if (obj.getClass().getName().equals("java.util.Date")) {
            return toString((java.util.Date) obj);
        }
        if (obj.getClass().getName().equals("java.lang.Float")) {
            return toString((Float) obj);
        }
        if (obj.getClass().getName().equals("java.sql.Timestamp")) {
            return toString((Timestamp) obj);
        }
        if (obj.getClass().getName().equals("java.lang.Double")) {
            return toString((Double) obj);
        }

        return obj.toString();
    }

}
