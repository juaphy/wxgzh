package com.jeecms.cms;

import java.io.Reader;
import java.security.NoSuchAlgorithmException;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import com.jeecms.cms.rest2.common.Page;
import com.jeecms.cms.rest2.entity.Node;

public class CTools {

	private static int iFileNameCount = 1000; // 产生文件名时用到的计数器
	private static int iJHIDCount = 1000; // 产生 JHID 时用到的计数器
	private static int bsnumCount =0;
	private static int iIDCount = 100;
	private static Object obj = new Object(); // 用于产生文件名的作锁定用
	private static Object oJhid = new Object(); // 用于产生 JHID 的作锁定用

	/**
	 * 转换日期字符串至 Timestamp 类型
	 * 
	 * @param strDate
	 *            日期字符串
	 * 
	 * @return Timestamp
	 * @throws java.lang.Exception
	 */
	public static Timestamp convertStringToTimestamp(String strDate)
			throws Exception {

		if (strDate == null || strDate.equals(""))
			return null;
		Timestamp t = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			t = new java.sql.Timestamp(format.parse(strDate).getTime());
		} catch (Exception e) {
			throw e;
		}
		return t;
	}

	/**
	 * 将 timestamp 类型转换为日期字符串
	 * 
	 * @param pTimestamp
	 *            输入 timestamp
	 * @return 日期字符串
	 * 
	 * @throws java.lang.Exception
	 */
	public static String convertTimestampToString(Timestamp pTimestamp)
			throws Exception {

		if (pTimestamp == null)
			return "";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.format(pTimestamp);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 业务流水号生成器
	 * 
	 * @return
	 */
	public static String serialnumberGenerator() {

		String sync = "serialNum";
		synchronized (sync) {
			String[] seed = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"A", "B", "C", "D", "E", "F", "G", "H", "I", "G", "H", "I",
					"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
					"V", "W", "X", "Y", "Z" };

			String[] serialNum = new String[10000];
			StringBuilder sd = new StringBuilder();
			for (int m = 0; m < 10000; m++) {
				for (int i = 0; i < 4; i++) {
					int index = (int) Math.round(Math.random() * 36);
					sd.append(seed[index]);
				}
				serialNum[m] = sd.toString();
				sd.delete(0, sd.length());
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			StringBuilder restSd = new StringBuilder();
			synchronized (restSd) {
				int irest = (int) Math.round(Math.random() * 10000);
				restSd.append(sdf.format(new Date()));
				restSd.append(serialNum[irest]);
				restSd.append("N");
			}
			sync = restSd.toString();
		}
		return sync;
	}

	/**
	 * 将 timestamp 类型转换为日期字符串
	 * 
	 * @param pTimestamp
	 *            Timestamp
	 * @param pFormat
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public static String convertTimestampToString(Timestamp pTimestamp,
			String pFormat) throws Exception {

		if (pTimestamp == null)
			return "";

		SimpleDateFormat format = new SimpleDateFormat(pFormat);
		try {
			return format.format(pTimestamp);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 将 timestamp 类型转换为日期字符串
	 * 
	 * @param strDate
	 *            String 输入 timestamp
	 * @param sFormat
	 *            String 输入 格式
	 * @return Timestamp
	 * @throws Exception
	 */
	public static Timestamp convertStringToTimestamp(String strDate,
			String sFormat) throws Exception {

		if (strDate == null || strDate.equals(""))
			return null;
		Timestamp t = null;
		SimpleDateFormat format = new SimpleDateFormat(sFormat);

		try {
			t = new java.sql.Timestamp(format.parse(strDate).getTime());
		} catch (Exception e) {
			throw e;
		}
		return t;
	}

	/**
	 * 获取当前时间的 Timestamp 类型
	 * 
	 * @return 当前时间的 Timestamp 类型
	 */
	public static Timestamp getCurrentTimestamp() {

		return new Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 获取当前时间的 String 类型
	 * 
	 * @return 当前时间的 String 类型
	 */
	public static String getCurrentTimeString() throws Exception {

		try {
			return getTimeString("yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取当前时间的 String 类型
	 * 
	 * @return 当前时间的 String 类型
	 */
	public static String getTimeString(String sFormat) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat(sFormat);
		try {
			return format.format(new java.util.Date());
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 从 byteBuffer 中获取字符串
	 * 
	 * @param byteBuffer
	 *            从 channel 中得到的 byteBuffer
	 * @return 字符串
	 * 
	 */
	public static String bufferToString(java.nio.ByteBuffer byteBuffer) {

		@SuppressWarnings("unused")
		int intLimit = byteBuffer.limit();
		byte[] bytes = new byte[byteBuffer.limit()];
		System.arraycopy(byteBuffer.array(), 0, bytes, 0, byteBuffer.limit());
		return new String(bytes);
	}

	/**
	 * 向 byteBuffer 中写入信息
	 * 
	 * @param byteBuffer
	 *            从 channel 中得到的 byteBuffer
	 * @param message
	 *            要写入的信息
	 */
	public static void addMsgToBuffer(java.nio.ByteBuffer byteBuffer,
			String message) {

		byteBuffer.clear();
		byteBuffer.put(message.getBytes());
		byteBuffer.flip();
	}

	/**
	 * 按照命名规则生成发送方的文件名，发送用
	 * 
	 * @param sStartDept
	 *            开始单位，既是源单位
	 * 
	 * @param sEndDept
	 *            结束单位，既是目标单位
	 * 
	 * @return 文件名
	 * 
	 * @throws java.lang.Exception
	 */
	public static String createFileName(String sStartDept, String sEndDept)
			throws Exception {

		synchronized (obj) {
			if (iFileNameCount >= 9999)
				iFileNameCount = 1000;
			StringBuffer sb = new StringBuffer(24);
			sb.append(sEndDept);
			sb.append(sStartDept);
			sb.append(getTimeString("MMddHHmmss"));
			sb.append(iFileNameCount++);
			sb.append(".xml");
			return sb.toString();
		}
	}

	/**
	 * 产生一个 21 位长的文件名，接收用
	 * 
	 * @return 文件名
	 * 
	 */
	public static String createFileName() {

		synchronized (obj) {
			if (iFileNameCount >= 9999)
				iFileNameCount = 1000;
			StringBuffer sb = new StringBuffer(24);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append("R"); // Receive file
			sb.append(format.format(new java.util.Date()));
			sb.append(iFileNameCount++);
			// sb.append(".xml");
			return sb.toString();
		}
	}

	/**
	 * 产生一个 19 位长的 JHID，第 20、21 位由 sp_createIterateWork 内产生
	 * 
	 * @return JHID
	 */
	public static String createJHID() {

		synchronized (oJhid) {
			if (iJHIDCount >= 9999)
				iJHIDCount = 1000;
			StringBuffer sb = new StringBuffer(19);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append("9");
			sb.append(format.format(new java.util.Date()));
			sb.append(iJHIDCount++);
			return sb.toString();
		}
	}

	/**
	 * 得到数据库的类型
	 * 
	 * @param sType
	 *            数据库名称
	 * 
	 * @return 数据库类型
	 * 
	 */
	public static int getSqlTypes(String sType) {

		if (sType.equalsIgnoreCase("Date")
				|| sType.equalsIgnoreCase("DateTime"))
			return java.sql.Types.DATE;
		if (sType.equalsIgnoreCase("CHAR"))
			return java.sql.Types.CHAR;
		if (sType.equalsIgnoreCase("VARCHAR"))
			return java.sql.Types.VARCHAR;
		if (sType.equalsIgnoreCase("INTEGER"))
			return java.sql.Types.INTEGER;
		if (sType.equalsIgnoreCase("NUMERIC"))
			return java.sql.Types.NUMERIC;
		if (sType.equalsIgnoreCase("DECIMAL"))
			return java.sql.Types.DECIMAL;
		return -99; // 一个不存在的类型

	}

	/**
	 * 数组扩容
	 * 
	 * @param src
	 *            byte[] 源数组数据
	 * 
	 * @param size
	 *            int 扩容的增加量
	 * @return byte[] 扩容后的数组
	 */
	public static byte[] grow(byte[] src, int size) {

		byte[] tmp = new byte[src.length + size];
		System.arraycopy(src, 0, tmp, 0, src.length);
		return tmp;
	}

	/**
	 * 数组扩容
	 * 
	 * @param src
	 *            char[] 源数组数据
	 * 
	 * @param size
	 *            int 扩容的增加量
	 * @return char[] 扩容后的数组
	 */
	public static char[] grow(char[] src, int size) {

		char[] tmp = new char[src.length + size];
		System.arraycopy(src, 0, tmp, 0, src.length);
		return tmp;
	}

	/**
	 * 对输入字符串按指定字符集转码，其中有几种字符集不需要转码
	 * 
	 * @param str
	 *            需要进行转码的字符串
	 * 
	 * @param sCharset
	 *            输出字符串使用的字符集
	 * 
	 * @return 转码后的字符串
	 * 
	 * @throws java.lang.Exception
	 */
	public static String strEncode(String str, String sCharset)
			throws Exception {

		/*
		 * JVM对字符集的处理： JVM核心完全使用Unicode字符集，编码上采用UTF-16LE(x86和Unix)。 Java编译器扫描.
		 * java源文件时将完成预转换，比如在中文Windows上编译.java文件时，你可能已经注意
		 * 
		 * 到.java文件中的字符串和.class中的不一样。因为.java文件本身用的是gb2312编码，
		 * 
		 * 而.class内则是UTF-16LE编码。如果你的编辑器支持，你可能会选择直接用UTF-8来书
		 * 写.java源程序，这时Java编译器就会用UTF-8对源程序解码。
		 * 
		 */
		if (str == null)
			return null;

		// 如果是以下几种字符集，则不需要进行转码

		if (sCharset.equalsIgnoreCase("GB2312")
				|| sCharset.equalsIgnoreCase("GBK")
				|| sCharset.equalsIgnoreCase("UTF8")
				|| sCharset.equalsIgnoreCase("UTF16")) {
			return str;
		}

		String sTemp;
		try {
			sTemp = new String(str.getBytes(), sCharset);
		} catch (java.io.UnsupportedEncodingException uee) {
			throw uee;
		}

		return sTemp;
	}

	/**
	 * 对按指定字符集编码的输入字符串转码为 JAVA 默认字符集的字符串
	 * 
	 * @param str
	 *            采用指定字符集编码的字符串
	 * 
	 * @param sCharset
	 *            指定的字符集
	 * @return JAVA 默认字符集的字符串
	 * 
	 * @throws java.lang.Exception
	 */
	public static String strDecode(String str, String sCharset)
			throws Exception {

		if (str == null)
			return null;

		// 如果是以下几种字符集，则不需要进行转码

		if (sCharset.equalsIgnoreCase("GB2312")
				|| sCharset.equalsIgnoreCase("GBK")
				|| sCharset.equalsIgnoreCase("UTF8")
				|| sCharset.equalsIgnoreCase("UTF16")) {
			return str;
		}

		String sTemp;
		try {
			sTemp = new String(str.getBytes(sCharset));
		} catch (java.io.UnsupportedEncodingException uee) {
			throw uee;
		}
		return sTemp;
	}

	/**
	 * 将 XML 的保留符号（<、>、&、'、"）替换成对应的转义字符
	 * 
	 * @param str
	 *            输入字符串
	 * 
	 * @return 转义后的字符串
	 * 
	 */
	public static String toXMLStr(String str) {

		if (str == null)
			return null;

		if (str.length() < 1)
			return str;

		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("'", "&apos;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}

	/**
	 * 将 List 集合中的字符转换为 'value1','value2','value3'...，用于 SQL 查询
	 * 
	 * @param list
	 *            AbstractList
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public static String listToString(AbstractList list) {
		StringBuffer sReturn = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sReturn.append("'").append((String) list.get(i)).append("',");
		}
		return sReturn.substring(0, sReturn.length() - 1);
	}

	public static String arrayToString(String[] args) {
		StringBuffer sReturn = new StringBuffer();
		sReturn.append("(");
		for (int i = 0; i < args.length; i++) {
			sReturn.append("'").append(args[i]).append("',");
		}
		return sReturn.substring(0, sReturn.length() - 1) + ")";
	}

	/**
	 * 取输入的字节的 MD5 摘要
	 * 
	 * @param strBytes
	 *            byte[] 输入的字节
	 * 
	 * @throws java.security.NoSuchAlgorithmException
	 * @return byte[] MD5 摘要
	 */
	public static byte[] getMD5Digest(byte[] strBytes)
			throws java.security.NoSuchAlgorithmException {

		java.security.MessageDigest mdTemp = java.security.MessageDigest
				.getInstance("MD5");
		mdTemp.update(strBytes);

		return mdTemp.digest();
	}

	/**
	 * 取输入字符串的 MD5 摘要（字符串形式）
	 * 
	 * @param str
	 *            String 输入字符串
	 * 
	 * @throws NoSuchAlgorithmException
	 * @return String MD5 摘要（字符串形式）
	 * 
	 */
	public static String getMD5Digest(String str)
			throws java.security.NoSuchAlgorithmException {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
				'Y', 'Z' };

		byte[] md = getMD5Digest(str.getBytes());
		int j = md.length;
		char strOut[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			strOut[k++] = hexDigits[byte0 >>> 4 & 0xf];
			strOut[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(strOut);
	}

	/**
	 * 生成 18 位的编号
	 * 
	 * @return String
	 */
	public static String getNewID() {
		synchronized (oJhid) {
			if (iJHIDCount >= 9999)
				iJHIDCount = 1000;
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append(format.format(new java.util.Date()));
			sb.append(iJHIDCount++);
			return sb.toString();
		}
	}

	/**
	 * 生成Bsnum
	 * @return
	 */
	public static String getBsnum() {
		synchronized (oJhid) {
			if (bsnumCount >= 9999){
				bsnumCount = 0;
			}
			int ci = 10000 + bsnumCount;
			String rstr = (Integer.toString(ci)).substring(1);
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			sb.append(format.format(new java.util.Date()));
			sb.append(rstr);
			bsnumCount++;
//			sb.append("0");//用于标识数据来源，0表示网上办事大厅入口
			sb.append("01");		//用于标识数据来源，01表示网上办事大厅入口
			return sb.toString();
		}
	}
	
	/**
	 * 生成移动端申报业务流水号
	 * @return
	 */
	public static String getMobileBsnum() {
		synchronized (oJhid) {
			if (bsnumCount >= 9999){
				bsnumCount = 0;
			}
			int ci = 10000 + bsnumCount;
			String rstr = (Integer.toString(ci)).substring(1);
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			sb.append(format.format(new java.util.Date()));
			sb.append(rstr);
			bsnumCount++;
//			sb.append("1");//用于标识数据来源，1表示移动网上办事大厅入口
			sb.append("02"); 	//用于标识数据来源，02表示移动网上办事大厅入口
			return sb.toString();
		}
	}
	
	/**
	 * 生成18位的编号，最后一位加N
	 * 
	 * @return String
	 */
	public static String getNewIDN() {
		synchronized (oJhid) {
			if (iIDCount >= 999)
				iIDCount = 100;
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

			sb.append(format.format(new java.util.Date()));
			sb.append(iIDCount++);
			sb.append("N");
			return sb.toString();
		}
	}

	/**
	 * 生成 15 位的编号
	 * 
	 * @return String
	 */
	public static long getNewNumID() {
		synchronized (oJhid) {
			if (iIDCount >= 999)
				iIDCount = 100;
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");

			sb.append(format.format(new java.util.Date()));
			sb.append(iIDCount++);
			return Long.parseLong(sb.toString());
		}
	}

	public static String getDateStr(Date date) {
		String str = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		if (null != date) {
			str = format.format(date);
		}
		return str;
	}

	public static void main(String[] args) {

		// try{
		// System.out.println(CTools.getMD5Digest("123"));
		// }catch(NoSuchAlgorithmException e){
		// e.printStackTrace();
		// }
		for (int i = 0; i < 20; i++) {
			System.out.println(CTools.getBsnum());
		}
	}

	/**
	 * 2010-08-18 截取特定字符串后的字符串
	 * 
	 * @param str、flagStr
	 * @return endStr
	 */
	public static String getEndStr(String str, String flagStr) {
		String endStr;
		int index = str.indexOf(flagStr);

		if (index > 0) {
			endStr = str.substring(index + 2);
		} else {
			endStr = str;
		}
		return endStr;

	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean getSex(String s) {

		if (s.equals("Male")) {
			return true;
		}
		return false;
	}

	/**
	 * 为字符串每个字符添加一个字符
	 * 
	 * @param baseString
	 *            基础字符串
	 * @param addString
	 *            需要添加的字符
	 * @return 添加之后的字符串
	 */
	public static String getStringAdd(String baseString, String addString) {
		if (baseString != null && baseString.trim().length()>0) {
			char[] chars = baseString.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < chars.length; i++) {
				sb.append(chars[i] + addString);
			}
			return sb.length() > 1 ? sb.substring(0, sb.length() - 1) : sb.toString();
		}
		else {
			return "";
		}
	}

	/**
	 * 拼接分页查询sql<br/>
	 * ORACLE,SQLSERVER,DB2,达梦数据库通用
	 * 
	 * @param field
	 * @param from
	 * @param page
	 * @param size
	 * @return
	 */
	public static String joinPageQuerySql(final String field, final String from, final String page, final String size,
			final String orderBy) {
		int pageNum = 1;// 页数从1开始计数
		int sizeNum = SystemConstant.PAGE_SIZE;
		if (StringUtils.isNotBlank(page)) {
			pageNum = Integer.parseInt(page);
			if (pageNum < 1) {
				pageNum = 1;
			}
		}
		if (StringUtils.isNotBlank(size)) {
			sizeNum = Integer.parseInt(size);
			if (sizeNum < 0) {
				sizeNum = SystemConstant.PAGE_SIZE;
			}
		}
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(field);
        sb.append(" FROM (SELECT ROW_NUMBER() OVER (ORDER BY ");
        sb.append(StringUtils.isNotBlank(orderBy) ? orderBy : field);
        sb.append(") AS RN,PAGE1.* FROM(");
        sb.append(from);
        sb.append(") PAGE1 ) PAGE2 WHERE RN <= ");
        sb.append(pageNum * sizeNum);
        sb.append(" AND RN > ");
        sb.append((pageNum - 1) * sizeNum);
		return sb.toString();
	}
	
	/**
     * 拼接分页查询sql<br/>
     * ORACLE,SQLSERVER,DB2,达梦数据库通用
     * 
     * @param field
     * @param from
     * @param page
     * @param orderBy
     * @return
     */
    public static String joinPageQuerySql(String field, String from, Page page, String orderBy) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(field);
        sb.append(" FROM (SELECT ROW_NUMBER() OVER (ORDER BY ");
        sb.append(StringUtils.isNotBlank(orderBy) ? orderBy : field);
        sb.append(") AS RN,PAGE1.* FROM(");
        sb.append(from);
        sb.append(") PAGE1 ) PAGE2 WHERE RN <= ");
        sb.append(page.getEnd());
        sb.append(" AND RN > ");
        sb.append(page.getStart());
        return sb.toString();
    }

	/**
	 * like查询防止sql注入
	 * 
	 * @param key
	 * @return
	 */
	public static String escapeLike(String key) {
		return escapeLike(key, true);
	}

	/**
	 * like查询防止sql注入
	 * 
	 * @param key
	 * @param needPercent
	 * @return
	 */
	public static String escapeLike(String key, boolean needPercent) {
		if (StringUtils.isBlank(key)) {
			return null;
		}

		StringBuilder buf = new StringBuilder();
		if (needPercent) {
			buf.append("%");
		}
		
		int len = key.length();
		// 注意："]"不能处理
		for (int i = 0; i < len; i++) {
			char c = key.charAt(i);
			switch (c) {
			case '\'':
				buf.append("''");// 单引号替换成两个单引号
				break;
			case '[':
				buf.append("[[]");
				break;
			case '_':
				buf.append("[_]");
				break;
			case '%':
				buf.append("[%]");
				break;
			default:
				buf.append(c);
			}
		}
		if (needPercent) {
			buf.append("%");
		}
		return buf.toString();
	}
	
	/**
	 * 将Clob转成String
	 * 
	 * @param clob
	 *            字段
	 * @return 内容字串，如果出现错误，返回 null
	 */
	public static String clobToString(Clob clob) {
		if (clob == null)
			return null;
		StringBuilder buf = new StringBuilder();
		Reader stream = null;
		try {
			stream = clob.getCharacterStream();
			char[] b = new char[10240];// 每次获取10K
			int i = 0;
			while ((i = stream.read(b)) != -1) {
				buf.append(b, 0, i);
			}
		} catch (Exception e) {
			buf = null;
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (Exception ignored) {
			}
		}
		if (buf == null)
			return null;
		else
			return buf.toString();
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static synchronized String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().toUpperCase();
	}
	
	/**
	 * 全角转半角
	 * @param input String.
	 * @return 半角字符串
	 */
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	/**
	 * 半角转全角
	 * @param input String.
	 * @return 全角字符串.
	 */
	public static String ToSBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = '\u3000';
			} else if (c[i] < '\177') {
				c[i] = (char) (c[i] + 65248);

			}
		}
		return new String(c);
	}
	
	/**
	 * 生成 20 位的编号
	 * 
	 * @return String
	 */
	public static String getMobileNewID() {
		synchronized (oJhid) {
			if (iJHIDCount >= 9999)
				iJHIDCount = 1000;
			StringBuffer sb = new StringBuffer(20);
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			sb.append(format.format(new java.util.Date()));
			sb.append(iJHIDCount++);
			sb.append("02"); 	//用于标识数据来源，02表示移动网上办事大厅入口
			return sb.toString();
		}
	}
	
	// 从XML中解析生成用户IDList
	public static List<String> Xml2UserIdList(String xml) {

		List<String> userIdList = new ArrayList();
		Node node = null;
		Document doc = null;
		try {
			if (xml != null && !xml.equals("") && xml.length() > 8
					&& xml.indexOf("<workflow") >= 0) {
				doc = org.dom4j.DocumentHelper.parseText(xml);

				List<Element> nodeList = null;
				nodeList = doc.selectNodes("//node");
				Iterator<Element> iter = nodeList.iterator();
				while (iter.hasNext()) {
					Element elements = iter.next();

					Element nodeActor = (Element) elements
							.selectSingleNode("nodeActor");
					List<Element> actorList = nodeActor.selectNodes("actor");
					Element e = null;
					Iterator<Element> iterator = actorList.iterator();
					while (iterator.hasNext()) {
						e = iterator.next();
						userIdList.add(e.attributeValue("id"));

					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return userIdList;

	}
	
	
	public static StringBuffer Sting2s(String userid) {
		StringBuffer sb = new StringBuffer();
		String[] s = null;
		if (userid.indexOf(",") > 0) {
			s = userid.split(",");
			for (int i = 0; i < s.length; i++) {
				sb.append("'");
				sb.append(s[i]);
				sb.append("'");
				sb.append(",");
			}
			sb.append("'    '");
		} else {
			sb.append("'");
			sb.append(userid);
			sb.append("'");
		}

		return sb;
	}

	/**
	 * 判断是否为空
	 * @param str
	 * @return 为null或者空字符串，则返回true
	 */
	public static boolean isEmpty(String str) {
	    return str == null || "".equals(str);
	}

}
