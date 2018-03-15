package com.jeecms.cms.action.front;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.JsonParser;
import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.entity.main.TCxjXjfwpd;
import com.jeecms.cms.manager.assist.CmsKeywordMng;
import com.jeecms.cms.manager.main.ContentMng;
import com.jeecms.cms.manager.main.CxjMng;
import com.jeecms.cms.rest2.CallRestMng;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.common.util.StringUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

/**
 * 查询机Act
 * @author swc 2018-03-14
 *
 */
@Controller
public class CxjAct {

    /** 查询机相关页面路径*/
    public static final String INDEXPAGE = "searchclient";

    @Autowired
    private ContentMng contentMng;

    @Autowired
    private CmsKeywordMng cmsKeywordMng;

    @Autowired
    private CxjMng cxjMng;

    /**
     * 中心简介/窗口布局/服务评价
     */
    @RequestMapping(value = "/cxzdj/zxjj.jspx", method = RequestMethod.GET)
    public String cxjIndex(HttpServletRequest request,HttpServletResponse response, ModelMap model) 
            throws JSONException {

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        Content content = contentMng.findById(Integer.valueOf(id));
        CmsSite site = CmsUtils.getSite(request);

        int pageNo = 1;
        String txt = content.getTxtByNo(pageNo);

        // 内容加上关键字
        txt = cmsKeywordMng.attachKeyword(site.getId(), txt);

        model.put("content", content);
        model.put("txt", txt);
        model.put("title", content.getTitleByNo(pageNo));

        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return FrontUtils.getTplPath(site.getSolutionPath(), INDEXPAGE, "zxjj");
    }

    /**
     * 星级服务评定
     */
    @RequestMapping(value = "/cxzdj/xjfwpd.jspx", method = RequestMethod.GET)
    public String xjfwpd(HttpServletRequest request,HttpServletResponse response, ModelMap model) 
            throws JSONException {

        CmsSite site = CmsUtils.getSite(request);
        Map<String, String> params = new HashMap<String, String>();
        List<TCxjXjfwpd> xjfwpdList = cxjMng.findXjfwpdList(params);
        model.put("xjfwpdList", xjfwpdList);
        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return FrontUtils.getTplPath(site.getSolutionPath(), INDEXPAGE, "xjfwpd");
    }

    /**
     * 办事指南-部门列表
     */
    @RequestMapping(value = "/cxzdj/bszn.jspx", method = RequestMethod.GET)
    public String bszn(HttpServletRequest request,HttpServletResponse response, ModelMap model) 
            throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String areaId = request.getParameter("areaId");

        // 获取部门列表
        List<DeptInfo> jsonDeptList = cxjMng.findDeptListByAreaid(areaId);
        model.put("deptList", jsonDeptList);

        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return FrontUtils.getTplPath(site.getSolutionPath(), INDEXPAGE, "deptlist");
    }

    /**
     * 办事指南-事项列表
     */
    @RequestMapping(value = "/cxzdj/bsznsxlb.jspx", method = RequestMethod.GET)
    public String bsznSxlb(HttpServletRequest request,HttpServletResponse response, ModelMap model) 
            throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        String deptId = request.getParameter("deptId");

        // 获取部门列表
        List<SxInfo> jsonSxList = cxjMng.findSxlbByDeptid(deptId);
        model.put("sxList", jsonSxList);

        try {
            String deptName = new String(request.getParameter("deptName").getBytes("iso8859-1"),"UTF-8");
            model.put("deptName", deptName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FrontUtils.frontData(request, model, site);
        FrontUtils.frontPageData(request, model);
        return FrontUtils.getTplPath(site.getSolutionPath(), INDEXPAGE, "sxlist");
    }

}
