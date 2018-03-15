package com.jeecms.cms.webservices.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Map.Entry;

public class PropertiesUtil {

	public static Map getProperties(String filePath) {
		Map rMap = new TreeMap();

		Properties pp = new Properties();
		try {
			pp.load(new FileInputStream(filePath));

			Iterator it = pp.entrySet().iterator();

			while (it.hasNext()) {
				Entry en = (Entry) it.next();
				String key = (String) en.getKey();
				String value = (String) en.getValue();
				rMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return rMap;
	}

	public static String mapValue(Map pMap, String key) {
		return pMap.get(key) == null ? "" : (String) pMap.get(key);
	}

	// ------------------------------2014.04.02罗勇添加-----------------
	private static Properties jdbc = new Properties();
	private static Properties system = new Properties();

	private PropertiesUtil() {
	}

	public static Properties getJdbc() {
		return jdbc;
	}

	public static void setJdbc(Properties jdbc) {
		PropertiesUtil.jdbc = jdbc;
	}

	public static Properties getSystem() {
		return system;
	}

	public static void setSystem(Properties system) {
		PropertiesUtil.system = system;
	}
	
	public static String getJdbcProperty(String key) {
		return jdbc.getProperty(key);
	}

	public static String getJdbcProperty(String key, String defaultValue) {
		String value = jdbc.getProperty(key);
		if (value == null || value.trim().isEmpty()) {
			return defaultValue;
		}
		return value;
	}
	
	public static String getSystemProperty(String key) {
		return system.getProperty(key);
	}

	public static String getSystemProperty(String key, String defaultValue) {
		String value = system.getProperty(key);
		if (value == null || value.trim().isEmpty()) {
			return defaultValue;
		}
		return value;
	}

}
