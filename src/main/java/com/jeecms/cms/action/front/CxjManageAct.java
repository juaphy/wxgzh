package com.jeecms.cms.action.front;

import static com.jeecms.core.manager.AuthenticationMng.AUTH_KEY;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.Constants;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjZxjjckbj;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.manager.main.CxjManageMng;
import com.jeecms.cms.manager.main.CxjMng;
import com.jeecms.common.page.Pagination;
import com.jeecms.common.page.SimplePage;
import com.jeecms.common.util.IDFactory;
import com.jeecms.common.util.StringUtils;
import com.jeecms.common.web.ResponseUtils;
import com.jeecms.common.web.session.SessionProvider;
import com.jeecms.core.entity.Authentication;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.manager.AuthenticationMng;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 查询机Act
 * @author swc 2018-03-14
 * @param <TCxjMenu>
 *
 */
@Controller
public class CxjManageAct {

    /** 查询机相关页面路径*/
    public static final String INDEXPAGE = "searchclient";

    @Autowired
    private CxjMng cxjMng;

    @Autowired
    private CxjManageMng cxjManageMng;

    @Autowired
    private SessionProvider session;

    @Autowired
    private AuthenticationMng authMng;

    /**
     * 查询机管理模块-菜单列表
     * @param request
     * @param response
     * @param model
     * @return
     * @throws JSONException
     */
    @RequestMapping(value="/cxj/manage/menulist.jspx", method = RequestMethod.GET)
    public String cxjManageMenuList(HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, "manage");
    }

    /**
     * 查询机管理模块-访问中心简介/窗口布局列表
     * @param request
     * @param response
     * @param map
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/cxj/manage/zxjjlist.jspx")
    public String cxjManageZxjjList(HttpServletRequest request, Integer pageNo, HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Pagination page = cxjManageMng.findZxjjckbjListForPage(Constants.SYSKEY_AREAID, type, status, SimplePage.cpn(pageNo), 20);
        model.put("pagination", page);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, "manage_zxjj_list");
    }

    /**
     * 查询机管理模块-访问中心简介或者窗口布局详情
     * @param request
     * @param response
     * @param model
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/cxj/manage/showZxjjCkbjInfo.jspx")
    public String showZxjjCkbjInfo(HttpServletRequest request,HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String pagePath = "manage_zxjj_view";
        String id = request.getParameter("id");
        TCxjZxjjckbj info = cxjManageMng.findTCxjZxjjckbjInfo(id);
        model.put("info", info);
        
        String procType = request.getParameter("procType");
        if ("update".equals(procType)) {
            pagePath = "manage_zxjj_edit";
        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, pagePath);
    }

    // 保存/更新中心简介或窗口布局的信息
    @RequestMapping(value = "/cxj/manage/saveOrUpdateZxjjCkbjInfo.jspx", method = RequestMethod.POST)
    public void saveOrUpdateZxjjCkbjInfo(HttpServletRequest request, HttpServletResponse response) {
        String authId = (String) session.getAttribute(request, AUTH_KEY);
        JSONObject json = new JSONObject();
        Authentication auth = null;
        if (authId != null) {
            // 存在认证ID
            auth = authMng.retrieve(authId);
            if (auth == null) {
                json.put("msg", "系统未检测到您的登录信息，请重新登录。");
                ResponseUtils.renderJson(response, json.put("msg", "success").toString());
                return;
            }
        }
        
        // 获取页面参数
        String procType = request.getParameter("procType");
        if (StringUtils.isEmpty(procType)) {
            json.put("msg", "系统参数异常。");
            ResponseUtils.renderJson(response, json.put("msg", "success").toString());
            return;
        }
        boolean isProcResult = false; // 处理结果
        if ("update".equals(procType)) {
            String id = request.getParameter("id");
            TCxjZxjjckbj info = cxjManageMng.findTCxjZxjjckbjInfo(id);
            if (info == null) {
                json.put("msg", "未查询到相关信息。");
                ResponseUtils.renderJson(response, json.put("msg", "success").toString());
                return;
            }
            info.setAreaid(Constants.SYSKEY_AREAID); // 区划id
            info.setTitle(request.getParameter("title")); // 标题
            String orderid = request.getParameter("orderid");
            if (!StringUtils.isEmpty(orderid)) {
                info.setOrderid(StringUtils.strToInteger(request.getParameter("orderid"))); // 排序编号
            }
            setTCxjZxjjckbjInfo(info, request, "status"); // 状态（0-无效、1-有效）
            setTCxjZxjjckbjInfo(info, request, "content"); // 设置内容
            info.setUpdateTime(new Date()); // 更新时间
            info.setUpdateUserid(auth.getId()); // 更新人id

            cxjManageMng.updateTCxjZxjjckbj(info);
            isProcResult = true;
        }

        if (isProcResult) {
            json.put("msg", "success").toString();
        } else {
            json.put("msg", "error").toString();
        }
        ResponseUtils.renderJson(response, json.toString());
    }

    /**
     * 设置中心简介/窗口布局信息
     * @param info
     * @param request
     * @param paramName
     */
    private void setTCxjZxjjckbjInfo(TCxjZxjjckbj info, HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        if (!StringUtils.isEmpty(value)) {
            info.setContent(value); // 设置内容
        }
    }

    /**
     * 查询机管理模块-访问星级服务评定列表页面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/cxj/manage/xjfwpdlist.jspx")
    public String xjfwpdlist(HttpServletRequest request, Integer pageNo, HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String type = request.getParameter("type");
        String status = request.getParameter("status");
        Pagination page = cxjManageMng.findXjfwpdListForPage(Constants.SYSKEY_AREAID, type, status, SimplePage.cpn(pageNo), 20);
        model.put("pagination", page);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, "manage_xjfwpd_list");
    }

    // 保存/更新星级服务评定的信息
    @RequestMapping(value = "/cxj/manage/saveOrUpdateXjfwpdInfo.jspx", method = RequestMethod.POST)
    public void saveOrUpdateXjfwpdInfo(HttpServletRequest request, HttpServletResponse response) {
        String authId = (String) session.getAttribute(request, AUTH_KEY);
        JSONObject json = new JSONObject();
        Authentication auth = null;
        if (authId != null) {
            // 存在认证ID
            auth = authMng.retrieve(authId);
            if (auth == null) {
                json.put("msg", "系统未检测到您的登录信息，请重新登录。");
                ResponseUtils.renderJson(response, json.put("msg", "success").toString());
                return;
            }
        }

        // 获取页面参数
        String procType = request.getParameter("procType");
        if (StringUtils.isEmpty(procType)) {
            json.put("msg", "系统参数异常。");
            ResponseUtils.renderJson(response, json.put("msg", "success").toString());
            return;
        }
        boolean isProcResult = false; // 处理结果
        if ("update".equals(procType)) {
            String id = request.getParameter("id");
            TCxjXjfwpd info = cxjManageMng.findTCxjXjfwpdInfo(id);
            if (info == null) {
                json.put("msg", "未查询到相关信息。");
                ResponseUtils.renderJson(response, json.put("msg", "success").toString());
                return;
            }
            TCxjXjfwpd.setXjfwpdInfo(info, request);
            cxjManageMng.updateTCxjXjfwpd(info);
            isProcResult = true;
        } else if ("add".equals(procType)) {
            TCxjXjfwpd info = new TCxjXjfwpd();
            info.setId(IDFactory.getId());
            info.setAreaid(Constants.SYSKEY_AREAID);
            info.setCreatetime(new Date());
            info.setSendid(auth.getId());
            info.setSendname(auth.getUsername());
            TCxjXjfwpd.setXjfwpdInfo(info, request);
            cxjManageMng.saveTCxjXjfwpd(info);
            isProcResult = true;
        } else {
            json.put("msg", "系统参数异常。");
            ResponseUtils.renderJson(response, json.put("msg", "success").toString());
            return;
        }

        if (isProcResult) {
            json.put("msg", "success").toString();
        } else {
            json.put("msg", "error").toString();
        }
        ResponseUtils.renderJson(response, json.toString());
    }

    /**
     * 查询机管理模块-访问星级服务评定详情页面
     * @param request
     * @param response
     * @param model
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/cxj/manage/showXjfwpdInfo.jspx")
    public String showXjfwpdInfo(HttpServletRequest request,HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String pagePath = "manage_xjfwpd_view";
        String id = request.getParameter("id");

        String procType = request.getParameter("procType");
        boolean isFindInfo = true; // 是否查询信息
        if ("update".equals(procType)) {
            pagePath = "manage_xjfwpd_edit";
        } else if ("add".equals(procType)) {
            pagePath = "manage_xjfwpd_add";
            isFindInfo = false;
        }

        if (isFindInfo) {
            TCxjXjfwpd info = cxjManageMng.findTCxjXjfwpdInfo(id);
            model.put("info", info);
        }
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, pagePath);
    }

    /**
     * 查询机管理模块-访问自定义背景图片页面
     * @param request
     * @param response
     * @param model
     * @return
     * @throws JSONException
     */
    @RequestMapping(value = "/cxj/manage/toBackgroundImgSet.jspx")
    public String showBackgroundImgSet(HttpServletRequest request,HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        TCxjZwzxconfig zwzxConfig = cxjMng.findZwzxconfig();
        if (zwzxConfig == null) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        model.put("info", zwzxConfig);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, "manage_backgroundimg_edit");
    }

    // 保存/更新星级服务评定的信息
    @RequestMapping(value = "/cxj/manage/updateBackgroundImg.jspx", method = RequestMethod.POST)
    public void updateBackgroundImg(HttpServletRequest request, HttpServletResponse response) {
        String authId = (String) session.getAttribute(request, AUTH_KEY);
        JSONObject json = new JSONObject();
        Authentication auth = null;
        if (authId != null) {
            // 存在认证ID
            auth = authMng.retrieve(authId);
            if (auth == null) {
                json.put("msg", "系统未检测到您的登录信息，请重新登录。");
                ResponseUtils.renderJson(response, json.put("msg", "success").toString());
                return;
            }
        }

        // 获取页面参数
        String procType = request.getParameter("procType");
        if (StringUtils.isEmpty(procType)) {
            json.put("msg", "系统参数异常。");
            ResponseUtils.renderJson(response, json.put("msg", "success").toString());
            return;
        }
        String id = request.getParameter("id");
        TCxjZwzxconfig info = cxjMng.findZwzxconfigById(id);
        if (info == null) {
            json.put("msg", "未查询到相关信息。");
            ResponseUtils.renderJson(response, json.put("msg", "success").toString());
            return;
        }
        info.setBackgroundImg(request.getParameter("backgroundImg"));
        cxjMng.updateBackgroundImg(info);

        json.put("msg", "success").toString();
        ResponseUtils.renderJson(response, json.toString());
    }

}
