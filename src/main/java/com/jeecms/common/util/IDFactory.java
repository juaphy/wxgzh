package com.jeecms.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ID工厂（copy from dzjc 2017-11-07）
 * @author luozhixiong   
 * @date 2015-3-26 下午2:32:35  
 */

public class IDFactory {
	private static Object oJhid = new Object();
	private static int iJHIDCount = 10000;

	/**
	 * 获取新的ID(线程安全)
	 * @return
	 */
	public static String getId() {
		synchronized (oJhid) {
			if (iJHIDCount >= 99999)
				iJHIDCount = 10000;
			StringBuffer sb = new StringBuffer(19);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append(format.format(new Date()));
			sb.append("9");
			sb.append(iJHIDCount++);
			return sb.toString();
		}
	}
}
