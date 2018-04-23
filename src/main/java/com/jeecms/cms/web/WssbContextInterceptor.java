package com.jeecms.cms.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jeecms.cms.rest2.entity.WUser;
import com.jeecms.common.util.CheckMobile;
import com.jeecms.common.util.SessionUtils;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.CmsSiteMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;

/**
 * 微信公众号-网上申报上下文信息拦截器
 * 
 * 包括登录信息、权限信息、站点信息
 */
public class WssbContextInterceptor extends HandlerInterceptorAdapter {
	public static final String USER_COOKIE = "_user_id_cookie";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
	    String url = request.getServletPath();
	    if (url != null && !url.contains("/wssb")) {
	        return true;
	    }

	    WUser user = new WUser();
		if(user != null){
			CookieUtils.addCookie(request, response, USER_COOKIE, user.getUserId(), null, null,"/");
		}

		return true;
	}
	
	/**
	 * 检查访问方式
	 */
	public void checkEquipment(HttpServletRequest request,HttpServletResponse response){
	}
	
	private CmsSite getByCookie(HttpServletRequest request) {
		Cookie cookie = CookieUtils.getCookie(request, USER_COOKIE);
		if (cookie != null) {
			String v = cookie.getValue();
			if (!StringUtils.isBlank(v)) {
				try {
					Integer siteId = Integer.parseInt(v);
					return cmsSiteMng.findById(siteId);
				} catch (NumberFormatException e) {
				}
			}
		}
		return null;
	}

	@Autowired
	private CmsSiteMng cmsSiteMng;

	@Autowired
	private SessionProvider session;

}