package com.jeecms.cms.action.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.Constants;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.cxj.TCxjMenu;
import com.jeecms.cms.manager.main.CxjMng;
import com.jeecms.core.entity.CmsSite;
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

    /**
     * 查询机管理模块-登录
     * @param request
     * @param response
     * @param model
     * @return
     * @throws JSONException
     */
    @RequestMapping(value="/cxj/manage/login.jspx", method = RequestMethod.GET)
    public String cxjHome(HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);

        TCxjZwzxconfig zwzxConfig = cxjMng.findZwzxconfig();
        if (zwzxConfig == null) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        List<TCxjMenu> menus = cxjMng.findCxjMenu(zwzxConfig.getAreaid());
        if (menus == null || menus.size() <= 0) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        model.put("menus", menus);
        model.put("zwzxConfig", zwzxConfig);
        FrontUtils.frontData(request, model, site);
        return FrontUtils.getTplPath(site.getSolutionPath(), Constants.TPLDIR_MNG_CXJINDEX, "login");
    }

}
