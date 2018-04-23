package com.jeecms.cms.action.wxgzh.wssb;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.Constants;
import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
// import com.jeecms.cms.manager.main.CallInterfaceMng;
import com.jeecms.cms.manager.main.WebHallIncInforMationMng;
import com.jeecms.cms.manager.main.WebHallUserInforMationMng;
import com.jeecms.common.security.DisabledException;
import com.jeecms.common.util.MD5;
import com.jeecms.common.web.RequestUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.manager.CmsUserMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 微信端网上申报-登录action
 * @author swc 2018-04-16
 *
 */
@RequestMapping("/wssb")
public class WssbLoginAction2 extends MyAct {

    @Autowired
    private AuthenticationMng authMng;

    @Autowired
    private SessionProvider session;
    
    @Autowired
    private CmsUserMng cmsUserMng;

    @Autowired
    private WebHallUserInforMationMng userinfoMng;

    @Autowired
    private WebHallIncInforMationMng incinfoMng;

    private static final String Userinfourl="/rest/login";

    @RequestMapping(value = "/logindex.jspx")
    public String loginindex(HttpServletRequest request,
            HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        String isNotUser = request.getParameter("isNotUser");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        String typecss=request.getParameter("typecss");
        try {
            String authId = (String) session.getAttribute(request, AuthenticationMng.AUTH_KEY);
            if(authId!=null && !"true".equals(isNotUser)){
                /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                        LB_WECHAT, WECHAT_USERCENTER);*/
                //request.getRequestDispatcher("/wechat/center.jspx").forward(request, response);
                
                return "redirect:/wssb/center.jspx";
            }
            model.put("typecss", typecss);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setFrontProperties(model, request);
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "zwfw_login");
    }

    @RequestMapping(value="/login.jspx",method = RequestMethod.POST)
    public String login(HttpServletRequest request,
            HttpServletResponse response, ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId=request.getParameter("areaId");
        String password=request.getParameter("password");
        String account=request.getParameter("account");
        String type=request.getParameter("type");//用户类型
        String lcip=request.getSession().getServletContext().getInitParameter("langchaopath");
        if(account==null||"".equals(account)){
            /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                    LB_WECHAT, WECHAT_LOGININDX);*/
            return "redirect:/wechat/logindex.jspx";
        }
        if(password==null||"".equals(password)){
            /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                    LB_WECHAT, WECHAT_LOGININDX);*/
            return "redirect:/wechat/logindex.jspx";
        }
        if(type==null||"".equals(type)){
            return "redirect:/wechat/logindex.jspx";
        }
        boolean Islogin=false;
        try {
            String ip = RequestUtils.getIpAddr(request);

            Map<String,String> userMap=new HashMap<String, String>();
            password = MD5.MD5Encode(password);
            userMap.put("utype", type);
            userMap.put("account", account);
            userMap.put("password", password);

            Map<String, Object> secureAttrs = null;
            // Map<String,Object> mapAll = null;

            //secureAttrs = client.buildUserInfo(postResult, key, key);
            secureAttrs = getSecureAttrs(userMap,lcip+Userinfourl);
            Authentication auth = null;
            
            CmsUser user=null;
            if (secureAttrs == null) {
                model.put("ischeck", "密码或账号不正确，请重新输入");
                return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "zwfw_login");
            } else { 
                //不为空则登录成功
                account=secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";
                auth = authMng.login(account,ip,request, response, session);
                if(auth==null){
                    //新注册过的账号，入库
                    saveUser(secureAttrs, ip);
                    auth = authMng.login(account,ip,request, response, session);
                    cmsUserMng.updateLoginInfo(auth.getUid(), ip);
                    user = cmsUserMng.findById(auth.getUid());
                    Islogin=true;
                }else{
                    cmsUserMng.updateLoginInfo(auth.getUid(), ip);
                    user = cmsUserMng.findById(auth.getUid());
                    String usertype = user.getUserType();
                    if(usertype.equals("1")){
                        TWebHallUserInforMation a = user.getUserInfoMation();
                        a = mapToBean(secureAttrs, a);
                        userinfoMng.update(a);
                        System.out.println(a.getUseruid());
                    }else{
                        TWebHallIncInforMation b=user.getIncInfoMation();
                        b=mapToBean(secureAttrs, b);
                        incinfoMng.updateIncInforMation(b);
                    }
                    Islogin=true;
                }
            }

            if (user.getDisabled()) {
                // 如果已经禁用，则推出登录。
                authMng.deleteById(auth.getId());
                session.logout(request, response);
                throw new DisabledException("user disabled");
            }
            removeCookieErrorRemaining(request, response);
            String gotoCerter="/wssb/center.jspx?areaId=" + areaId;

            // 跳转到网上申报页面
            String isWssb = request.getParameter("isWssb");
            String gotoUrl = request.getParameter("goto");
            String sxid = request.getParameter("sxid");
            if ("true".equals(isWssb)) {
                gotoCerter = gotoUrl + "?areaId=" + areaId + "&sxid=" + sxid;
            }

            if (Islogin) {
                /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                        LB_WECHAT, WECHAT_USERCENTER);*/
                //response.
                return "redirect:"+gotoCerter;
            } else {
                return "redirect:/wssb/logindex.jspx?areaId=" + areaId;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/wssb/logindex.jspx?areaId=" + areaId;
        }
    }

    @RequestMapping(value="/login4Wt.jspx",method = RequestMethod.POST)
    public String login4Wt(HttpServletRequest request,
            HttpServletResponse response, ModelMap model){
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId=request.getParameter("areaId");
        String password=request.getParameter("password");
        String account=request.getParameter("account");
        String type=request.getParameter("type");//用户类型
        String lcip=request.getSession().getServletContext().getInitParameter("langchaopath");
        if(account==null||"".equals(account)){
            /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                    LB_WECHAT, WECHAT_LOGININDX);*/
            return "redirect:/wechat/logindex.jspx";
        }
        if(password==null||"".equals(password)){
            /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                    LB_WECHAT, WECHAT_LOGININDX);*/
            return "redirect:/wechat/logindex.jspx";
        }
        if(type==null||"".equals(type)){
            return "redirect:/wechat/logindex.jspx";
        }
        boolean Islogin=false;
        try {
            String ip = RequestUtils.getIpAddr(request);
            
            Map<String,String> userMap=new HashMap<String, String>();
            password = MD5.MD5Encode(password);
            userMap.put("utype", type);
            userMap.put("account", account);
            userMap.put("password", password);
            
            Map<String, Object> secureAttrs = null;
            // Map<String,Object> mapAll = null;
            
            //secureAttrs = client.buildUserInfo(postResult, key, key);
            secureAttrs = getSecureAttrs(userMap,lcip+Userinfourl);
            Authentication auth = null;
            
            CmsUser user=null;
            if (secureAttrs == null) {
                model.put("ischeck", "密码或账号不正确，请重新输入");
                return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "zwfw_login");
            } else { 
                //不为空则登录成功
                account=secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";
                auth = authMng.login(account,ip,request, response, session);
                if(auth==null){
                    //新注册过的账号，入库
                    saveUser(secureAttrs, ip);
                    auth = authMng.login(account,ip,request, response, session);
                    cmsUserMng.updateLoginInfo(auth.getUid(), ip);
                    user = cmsUserMng.findById(auth.getUid());
                    Islogin=true;
                }else{
                    cmsUserMng.updateLoginInfo(auth.getUid(), ip);
                    user = cmsUserMng.findById(auth.getUid());
                    String usertype = user.getUserType();
                    if(usertype.equals("1")){
                        TWebHallUserInforMation a = user.getUserInfoMation();
                        a = mapToBean(secureAttrs, a);
                        userinfoMng.update(a);
                        System.out.println(a.getUseruid());
                    }else{
                        TWebHallIncInforMation b=user.getIncInfoMation();
                        b=mapToBean(secureAttrs, b);
                        incinfoMng.updateIncInforMation(b);
                    }
                    Islogin=true;
                }
            }
            
            if (user.getDisabled()) {
                // 如果已经禁用，则推出登录。
                authMng.deleteById(auth.getId());
                session.logout(request, response);
                throw new DisabledException("user disabled");
            }
            removeCookieErrorRemaining(request, response);
            String gotoCerter="/wssb/center.jspx?areaId=" + areaId;
            
            // 跳转到网上申报页面
            String isWssb = request.getParameter("isWssb");
            String gotoUrl = request.getParameter("goto");
            String sxid = request.getParameter("sxid");
            if ("true".equals(isWssb)) {
                gotoCerter = gotoUrl + "?areaId=" + areaId + "&sxid=" + sxid;
            }
            
            if (Islogin) {
                /*return FrontUtils.getTplPath(request, site.getSolutionPath(),
                        LB_WECHAT, WECHAT_USERCENTER);*/
                //response.
                return "redirect:"+gotoCerter;
            } else {
                return "redirect:/wssb/logindex.jspx?areaId=" + areaId;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/wssb/logindex.jspx?areaId=" + areaId;
        }
    }

    // 跳到个人中心页面
    @RequestMapping(value = "/center.jspx")
    public String center(HttpServletRequest request,
            HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        String typecss=request.getParameter("typecss");
        model.put("typecss", typecss);
        if(user == null){
            return "redirect:/wssb/logindex.jspx?isNotUser=true&areaId=" + areaId;
        }
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "user_center");
    }

    @RequestMapping(value = "/logout.jspx")
    public String logout(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
        String authId = (String) session.getAttribute(request, AuthenticationMng.AUTH_KEY);
        String areaId = request.getParameter("areaId");
        if (authId != null) {
            authMng.deleteById(authId);
            // session.logout(request, response);
            session.logout2(request, response, AuthenticationMng.AUTH_KEY);
        }
        return "redirect:/wssb/logindex.jspx?areaId=" + areaId;
    }

}
