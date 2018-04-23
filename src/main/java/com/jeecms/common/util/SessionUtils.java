package com.jeecms.common.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import com.sunwayworld.common.utils.StringUtils;
/**
 * 
 */
public class SessionUtils {

    public static String LOGIN_USERID = "LOGIN_USERID";// 用户登陆的id

    public static String LOGIN_USER = "LOGIN_USER";// 用户登录的基本信息

    public static String USER_INFO = "USER_INFO";// 用户登陆的个人详细信息

    public static String INC_INFO = "INC_INFO";// 用户登陆的企业详细信息

    public static String USER_XML = "USER_XML";
    
    public static String PAGE_CSS = "PAGE_CSS";
    
    public static String AREAID = "areaId";
    
    public static String WELCOME_FILE = "WELCOME_FILE";
    

    public static String getSessionid(HttpServletRequest request) {

	HttpSession session = request.getSession();
	return session.getId();
    }

    public static boolean isLogin(HttpServletRequest request) {

	Object obj = getUserInfo(request);
	return null != obj;

    }

    // 用户退出的时候清除
    public static void loginOut(HttpServletRequest request) {

	HttpSession session = request.getSession();
	session.removeAttribute(LOGIN_USERID);
	session.removeAttribute(LOGIN_USER);
	// session.removeAttribute(USER_INFO);
	// session.removeAttribute(INC_INFO);
    }

    public static String getUserId(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String userId = StringUtils.toString(session.getAttribute(LOGIN_USERID));
        return userId;
    }

    public static void setUserId(HttpServletRequest request, Serializable id) {

	HttpSession session = request.getSession();
	session.setAttribute(LOGIN_USERID, id);

    }

    // 获得用户登录的基本信息
    public static Object getUserInfo(HttpServletRequest request) {

	HttpSession session = request.getSession();
	return session.getAttribute(LOGIN_USER);

    }

    // 将用户登陆的基本信息放到session里面
    public static void setUserInfo(HttpServletRequest request, Object obj) {

	HttpSession session = request.getSession();
	session.setAttribute(LOGIN_USER, obj);
    }

    // 将用户登录个人的详细信息放到session里面
    public static void setUserInformation(HttpServletRequest request, Object obj) {

	HttpSession session = request.getSession();
	session.setAttribute(USER_INFO, obj);
    }

    public static void setUser_XMl(HttpServletRequest request, String xml) {

	HttpSession session = request.getSession();
	session.setAttribute(USER_XML, xml);
    }

    // 将用户登陆的企业基本信息放到session里面
    public static void setIncInfomation(HttpServletRequest request, Object obj) {

	HttpSession session = request.getSession();
	session.setAttribute(INC_INFO, obj);
    }
    
    //取得区划信息 
    public static String getAreaidInfo(HttpServletRequest request) {
    	HttpSession session = (HttpSession) request.getSession();
    	String areaid = (String) session.getAttribute(AREAID);
    	return areaid;
    }
    //取得区划信息,为空时，默认返回系统区划520000 
    public static String getAreaidInfoNull(HttpServletRequest request) {
    	HttpSession session = (HttpSession) request.getSession();
    	String areaid = (String) session.getAttribute(AREAID);
    	if(areaid == null || "".equals(areaid)) {
    		areaid = SystemConstant.AREAID;
    	}
    	return areaid;
    }
    //设置区划信息,obj为null时，默认系统区划520000
    public static void setAreaidInfo(HttpServletRequest request,String obj) {
    	HttpSession session = request.getSession();
    	if(obj != null && !"".equals(obj)) {
    		session.setAttribute(AREAID, obj);
    	}else {
    		session.setAttribute(AREAID, SystemConstant.AREAID);
    	}
    }
}
