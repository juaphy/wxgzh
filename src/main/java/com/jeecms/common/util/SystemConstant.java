/**
 * 
 */
package com.jeecms.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swc
 * @date 2018-03-20
 */
@SuppressWarnings("serial")
public final class SystemConstant{

	public static String ROOTPATH; // 加载ROOT目录
	
	public static String DBTYPE="ORACLE";
	public static String PLUGS="";
	public static String SYSTEMID="";
	public static final String CUserBean = "CUserBean";
	public static final int ID_LENGTH = 10;
	public static final long SYSTEM_MIN_ROLEWEIGHT = 100l;
	public static final String SYSTEM_STATUS_VALIDATE = "1";
	public static final String SYSTEM_STATUS_INVALIDATE = "0";
	public static final Long SYSTEM_ROLE_WEIGHT_SUPERADMIN = 1l;
	public static final Long SYSTEM_ROLE_WEIGHT_ADMIN = 2l;
	/** System Area User ** */
	public static final Long SYSTEM_ROLE_WEIGHT_AREA_ADMIN = 4l;

	public static final String MATERIAL_ONE = "1";
	public static final String MATERIAL_TWO = "2";
	public static final String MATERIAL_THREE = "3";
	public static final String MATERIAL_FOUR = "4";
	public static final String MATERIAL_FIVE = "5";
	public static final String MATERIAL_SIX = "6";
	public static final String MATERIAL_SEVEN = "7";
	public static final String MATERIAL_COMMITTED = "1";
	public static final String MATERIAL_UNCOMMITTED = "0";
	public static final String ROLE_POWER_VALUE_SYSMAG = "1";
	public static final String ROLE_POWER_VALUE_DEPMAG = "2";
	public static final String ROLE_POWER_VALUE_NORMAL = "3";
	public static final String ROLE_POWER_VALUE_KZ = "4";
	public static final String WEBHALL_BUSSINESS_ONE = "1";
	public static final String WEBHALL_BUSSINESS_TWO = "2";
	public static final String WEBHALL_BUSSINESS_THREE = "3";
	public static final String WEBHALL_BUSSINESS_FOUR = "4";
	public static final String WEBHALL_BUSSINESS_FIVE = "5";
	public static final String WEBHALL_BUSSINESS_SIX = "6";
	public static final String WEBHALL_BUSSINESS_SEVEN = "7";
	public static final String WEBHALL_BUSSINESS_EIGHT = "8";
	public static final String WEBHALL_BUSSINESS_NINE = "9";
	public static final String WEBHALL_BUSSINESS_TEN = "10";
	public static final String WEBHALL_BUSSINESS_ELEVEN = "11";
	public static final String TEMPLEMENT_ONE = "1";
	public static final String TEMPLEMENT_TWO = "2";
	public static final String TEMPLEMENT_THREE = "3";
	public static final String TEMPLEMENT_TYPE_ONE = "1";
	public static final String TEMPLEMENT_TYPE_TWO = "2";
	public static final String TEMPLEMENT_TYPE_THREE = "3";
	public static final String TEMPLEMENT_TYPE_FOUR = "x";
	public static final String TEMPLEMENT_TYPE_SEVEN = "7";
	public static final String TEMPLEMENT_TYPE_EIGHT = "8";
	public static final String SYSTEM_VALIDATE = "1";
	public static final String SYSTEM_INVALIDATE = "0";
	public static final String SYSTEM_CHARGE_TYPE_ZERO = "0";
	public static final String SYSTEM_CHARGE_TYPE_ONE = "1";
	public static final String SYSTEM_CHARGE_TYPE_TWO = "2";
	public static final String SYSTEM_CHARGE_TYPE_THREE = "3";
	public static final String COOPERATION_TO_DONE = "0";
	public static final String COOPERATION_HAS_DONE = "1";
	public static final String COOPERATION_NOT_DONE = "2";
	public static final String COOPERATION_WARN_STATE = "3";
	public static final String COOPERATION_OUT_TIME = "4";
	/***************************************************************************
	 * 业务类型
	 **************************************************************************/
	// 审批业务
	public static final String BUSINESS_TYPE_STATUS_ONE = "1";
	// 协同业务
	public static final String BUSINESS_TYPE_STATUS_TWO = "2";
	// 大厅业务
	public static final String BUSINESS_TYPE_STATUS_THREE = "3";
	public static final String SYSTEM_CHARGE_STATUS_ZERO = "0";
	public static final String SYSTEM_CHARGE_STATUS_ONE = "1";
	public static final String SYSTEM_CHARGE_STATUS_THREE = "3";
	public static final String SYSTEM_CHARGE_PROPERTY_TRUE = "0";
	public static final String SYSTEM_CHARGE_PROPERTY_FALSE = "1";
	public static final String BUSSINESS_PRINT_TYPE = "HTML";
	/***************************************************************************
	 * SYSTEM_KEY_VALUE-SYSTEMID(Example:SYSTEM-ROLE-ID-20090716160852100100)
	 **************************************************************************/
	// System Role XML
	public static final String SYSTEM_ROLE_KEY_VALUE = "SYSTEM-ROLE-ID-";
	// System Department XML
	public static final String SYSTEM_DEPT_KEY_VALUE = "SYSTEM-DEPT-ID-";
	// System User XML
	public static final String SYSTEM_USER_KEY_VALUE = "SYSTEM-USER-ID-";
	// System Help XML
	public static final String SYSTEM_HELP_KEY_VALUE = "SYSTEM-HELP-ID-";
	// System EMAIL COMFING XML
	public static final String SYSTEM_EMAIL_KEY_VALUE = "SYSTEM-EMAIL-ID-";
	// System SMS COMFING XML
	public static final String SYSTEM_SMS_KEY_VALUE = "SYSTEM-SMS-ID-";
	// System CMT COMFING XML
	public static final String SYSTEM_CMT_KEY_VALUE = "SYSTEM-CMT-ID-";
	// System ATTEND COMFING XML
	public static final String SYSTEM_ATD_KEY_VALUE = "SYSTEM-ATD-ID-";
	// System Configuration XML
	public static final String SYSTEM_CONFIGRUATION_KEY_VALUE = "SYSTEM-BASE-CONFIGURATION-LIJINFENG";
	// Admin Role
	public static final String ADMIN_ROLE = "";
	
	public static  String ISBATCHDELETEFILE="false";

	/**
	 * DefaultMenu = "37d2fde5-64ff-4f85-a88a-3fc294103642"
	 */
	public static final String MenuRootId = "37d2fde5-64ff-4f85-a88a-3fc294103642";

	/**
	 * EmptyString = "00000000000000000000";
	 */
	public static final String EmptyString = "00000000000000000000";
	/**
	 * MaxString = "99999999999999999999";
	 */
	public static final String MaxString = "99999999999999999999";
	/**
	 * SystemCode = "0001"
	 */
	public static final String SystemCode = "0001";

	/**
	 * 超级管理员 角色
	 */
	public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
	/**
	 * 应用管理员 角色
	 */
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static final String ROLE_AREA_ADMIN = "ROLE_AREA_ADMIN";
	/**
	 * 机构/部门管理员 角色
	 */
	public static final String ROLE_DEPARTMENT = "ROLE_DEPARTMENT";
	/**
	 * 应用系统业务操作人员 角色
	 */
	public static final String ROLE_BUSSINESS = "ROLE_BUSSINESS";
	
	/**
	 * 云平台system user id 
	 */
	public static final String cloudSystemUserId = "354548a4-d780-4369-9db2-63db1f2040cd";
	
	/**
	 * 本系统 system user id
	 */
	public static final String mySystemUserId = "00000000000000000000";
	
	/**
	 *云平台  endpoint 的 webServices 路径
	 */
	public static final String endpoint="http://192.9.207.250:9763/services/PlatformBaseServices/";
	/**
	 *云平台  extEndpoint 的 webServices 路径
	 */
	public static final String extEndpoint="http://192.9.207.250:9763/services/PlatformBaseServicesExt1.SOAP11Endpoint/";
	
	/**
	 * 取RoleValue 以云平台对应RoleValue值
	 */
	public static final Map<String, String> RoleValue = new HashMap<String, String>() {
		{
			put("SuperAdmin", "ROLE_SUPER_ADMIN");
			put("AppAdmin", "ROLE_ADMIN");			
			put("OrgAdmin", "ROLE_DEPARTMENT");
			put("AppBusiness", "ROLE_BUSSINESS");
		}
	};
	
	/**
	 * 取RoleValue 的权重值
	 */
	public static final Map<String, Integer> RoleHeight = new HashMap<String, Integer>() {
		{
			put("ROLE_SUPER_ADMIN", 1);
			put("ROLE_ADMIN", 2);		
			put("ROLE_AREA_ADMIN", 4);
			put("ROLE_DEPARTMENT", 8);
			put("ROLE_BUSSINESS", 16);
			put("ROLE_CHARGE", 32);
			put("ROLE_INSPECT", 64);
			put("ROLE_USER", 64);
			put("ROLE_OTHER", 128);
		}
	};
	
	/**
	 * 部门类型对应关系
	 */
	public static final Map<String, Integer> DeptType = new HashMap<String, Integer>() {
		{
			put("sp", 0);			
		}
	};
	
	/**
	 * 部门状态对应关系
	 */
	public static final Map<String, Integer> DeptStatus = new HashMap<String, Integer>() {
		{
			put("delete", 1);			
		}
	};

	
	public static String getISBATCHDELETEFILE() {
		return ISBATCHDELETEFILE;
	}
	public static void setISBATCHDELETEFILE(String iSBATCHDELETEFILE) {
		ISBATCHDELETEFILE = iSBATCHDELETEFILE;
	}
	public static String getDBTYPE() {
		return DBTYPE;
	}
	public void setDbtype(String dbtype){
		SystemConstant.DBTYPE = dbtype;
	}
	
	public void setPLUGS(String plugs){
		SystemConstant.PLUGS = plugs.toUpperCase();
	} 
	public void setSYSTEMID(String systemid) {
		SystemConstant.SYSTEMID = systemid;
	}
}
