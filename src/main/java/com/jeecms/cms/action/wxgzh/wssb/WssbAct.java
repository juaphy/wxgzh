package com.jeecms.cms.action.wxgzh.wssb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeecms.cms.CTools;
import com.jeecms.cms.Constants;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.manager.main.CallInterfaceMng;
import com.jeecms.cms.manager.main.WxgzhWssbMng;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

import net.sf.json.JSONException;

/**
 * 
 * @author VAIO
 *
 */
@RequestMapping("/wssb")
@Controller
public class WssbAct {

    @Autowired
    private WxgzhWssbMng wxgzhWssbMng;

    @Autowired
    private CallInterfaceMng callInterfaceMng;

    // 跳到首页
    @RequestMapping("/index.jspx")
    public String wssbIndex(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (CTools.isEmpty(areaId)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        TCxjZwzxconfig zwzxConfig = wxgzhWssbMng.findZwzxconfig(areaId);
        if (zwzxConfig == null) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        model.put("zwzxConfig", zwzxConfig);
        model.put("areaId", areaId);

        // 获取部门列表
        // 县区（市、区）级部门
        List<DeptInfo> xqDeptInfos = callInterfaceMng.findDeptListByAreaid(areaId, Constants.ISZSBM_XQ);
        model.put("xqDeptInfos", xqDeptInfos);

        // 乡（镇、街道、社区）
        List<DeptInfo> xzDeptInfos = callInterfaceMng.findDeptListByAreaid(areaId, Constants.ISZSBM_XZ);
        model.put("xzDeptInfos", xzDeptInfos);

        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "zwfw_bm_list");
    }

    // 跳到部门办事页面（事项列表）
    @RequestMapping("/bmbs/{deptId}.jspx")
    public String wsbsBmbs(@PathVariable String deptId,HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.WSSB_INDEX, "bsfw_bmsxlb");
    }

}
