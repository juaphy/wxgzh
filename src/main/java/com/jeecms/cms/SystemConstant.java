package com.jeecms.cms;

import java.util.HashMap;
import java.util.Map;

import com.jeecms.cms.webservices.util.PropertiesUtil;

public final class SystemConstant {

	/**
	 * 区划编号
	 */
	public static final String AREAID = "000001";

	/**
	 * CMS SystemCode = "GovWH"
	 */
	public static final String SystemCode = "GovWH";

	/**
	 * 云平台system user id
	 */
	public static final String cloudSystemUserId = "354548a4-d780-4369-9db2-63db1f2040cd";

	/**
	 * 本系统 system user id
	 */
	public static final String mySystemUserId = "00000000000000000000";

	/**
	 * 接口服务返回值
	 */
	public static final int OK 				      = 200;// OK: Success!
	public static final int NOT_MODIFIED 		  = 304;// Not Modified: There was no new data to return.
	public static final int BAD_REQUEST 		  = 400;// Bad Request: The request was invalid.  An accompanying error message will explain why. This is the status code will be returned during rate limiting.
	public static final int NOT_AUTHORIZED 	      = 401;// Not Authorized: Authentication credentials were missing or incorrect.
	public static final int FORBIDDEN 			  = 403;// Forbidden: The request is understood, but it has been refused.  An accompanying error message will explain why.
	public static final int NOT_FOUND             = 404;// Not Found: The URI requested is invalid or the resource requested, such as a user, does not exists.
	public static final int NOT_ACCEPTABLE        = 406;// Not Acceptable: Returned by the Search API when an invalid format is specified in the request.
	public static final int INTERNAL_SERVER_ERROR = 500;// Internal Server Error: Something is broken.  Please post to the group so the Weibo team can investigate.
	public static final int BAD_GATEWAY           = 502;// Bad Gateway: System is down or being upgraded.
	public static final int SERVICE_UNAVAILABLE   = 503;// Service Unavailable: The servers are up, but overloaded with requests. Try again later. The search and trend methods use this to indicate when you are being rate limited.
	/**
	 * 接口服务返回值提示信息
	 */
	public static final String OK_TIPS = "操作成功";
	public static final String NOT_MODIFIED_TIPS = "无修改，无返回新数据";
	public static final String BAD_REQUEST_TIPS = "错误的请求，请检查请求地址格式是否正确";
	public static final String NOT_AUTHORIZED_TIPS = "未通过授权";
	public static final String FORBIDDEN_TIPS = "无权访问";
	public static final String NOT_FOUND_TIPS = "没有找到指定的服务";
	public static final String NOT_ACCEPTABLE_TIPS = "指定的服务是无效的";
	public static final String INTERNAL_SERVER_ERROR_TIPS = "服务处理异常";
	public static final String BAD_GATEWAY_TIPS = "系统正在升级或维护中";
	public static final String SERVICE_UNAVAILABLE_TIPS = "服务不可用，请稍后再试";

	/**
	 * 数据库类型
	 */
	public static final String DATABASE_TYPE_MYSQL = "MYSQL";
	public static final String DATABASE_TYPE_ORACLE = "ORACLE";
	public static final String DATABASE_TYPE_SQLSERVER = "SQLSERVER";
	public static final String DATABASE_TYPE_DB2 = "DB2";
	public static final String DATABASE_TYPE_DM = "DM";

	public static final String param = "param";// 参数名称

	// 手机端web service接口 name space
	public static final String NS = "http://service.rest2.cms.jeecms.com/";

	// 默认分页大小
	public static final int PAGE_SIZE = 15;

	// 类别
	public static final String TYPE_ZX = "1";// 咨询
	public static final String TYPE_TS = "2";// 投诉

	// 咨询是否已回复
	public static final String ZX_WHF = "0";// 未回复
	public static final String ZX_YHF = "1";// 已回复

	// 数据是否有效
	public static final String STATUS_WX = "0";// 无效
	public static final String STATUS_YX = "1";// 有效

	// 业务办理状态
	public static final String BUSINESS_YSB = "0";// 已申报
	public static final String BUSINESS_YYS = "1";// 已预审
	public static final String BUSINESS_BLZ = "2";// 办理中
	public static final String BUSINESS_YBJ = "3";// 已办结
	public static final String BUSINESS_BJCL = "4";// 补交材料
	public static final String BUSINESS_YYDQR = "5";// 预约待确认
	public static final String BUSINESS_YYYQR = "6";// 预约已确认
	public static final String BUSINESS_YYYCX = "7";// 预约已撤销
	public static final String BUSINESS_BYSL = "8";// 不予受理
	public static final String BUSINESS_ZC = "9";// 暂存
	public static final String BUSINESS_YSBTG = "10";// 不通过

	// 用户类型[1个人，2企业（机关单位），9其他]
	public static final String U_TYPE_GR = "1";
	public static final String U_TYPE_QY = "2";

	// 服务状态，0未调用，1已调用
	public static final String SERVICE_STATUS_WDY = "0";// 未调用
	public static final String SERVICE_STATUS_YDY = "1";// 已调用

	public final static Map<String, String> BUSINESS = new HashMap<String, String>();
	static {
		BUSINESS.put(BUSINESS_YSB, "已申报");
		BUSINESS.put(BUSINESS_YYS, "预审通过");
		BUSINESS.put(BUSINESS_BLZ, "办理中");
		BUSINESS.put(BUSINESS_YBJ, "已办结");
		BUSINESS.put(BUSINESS_BJCL, "材料补正");
		BUSINESS.put(BUSINESS_YYDQR, "预约待确认");
		BUSINESS.put(BUSINESS_YYYQR, "预约已确认");
		BUSINESS.put(BUSINESS_YYYCX, "预约已撤销");
		BUSINESS.put(BUSINESS_BYSL, "不予受理"); 
		BUSINESS.put(BUSINESS_ZC, "暂存");
		BUSINESS.put(BUSINESS_YSBTG, "不通过");
	}

	//证件类型
    public final static Map<String, String> CERT_TYPE = new HashMap<String, String>();
    static {
        CERT_TYPE.put("10", "身份证");
        CERT_TYPE.put("11", "军人证");
        CERT_TYPE.put("12", "士兵证");
        CERT_TYPE.put("13", "警官证");
        CERT_TYPE.put("14", "港澳居民来往内地通行证");
        CERT_TYPE.put("15", "台湾居民来往大陆通行证");
        CERT_TYPE.put("16", "香港身份证");
        CERT_TYPE.put("17", "澳门身份证");
        CERT_TYPE.put("18", "台湾身份证");
        CERT_TYPE.put("20", "护照");
        CERT_TYPE.put("90", "其他");
        // 历史数据
        CERT_TYPE.put("1", "身份证");
        CERT_TYPE.put("2", "护照");
        CERT_TYPE.put("3", "军官证");
        CERT_TYPE.put("4", "退伍证");
        CERT_TYPE.put("5", "驾驶证");
        CERT_TYPE.put("6", "学生证");
        CERT_TYPE.put("7", "回乡证");
        CERT_TYPE.put("8", "台胞证");
    }

	//查询部门表时，TYPE的值
    public static final String DEPT_TYPE = PropertiesUtil.getSystemProperty("T_SYS_DEPT_TYPE", "1");

    // 材料类型 1：证照，2：附件，3：申请表
    public static final String CL_CERT = "1";
    public static final String CL_ATTACH = "2";
    public static final String CLBD_ATTACH = "3";
    //获取业务流水号方式，0本地，1接口
    public static final String SWITCH_YWLSH = PropertiesUtil.getSystemProperty("SWITCH_YWLSH", "0");
}
