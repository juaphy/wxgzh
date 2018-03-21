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
import com.jeecms.cms.manager.main.CxjManageMng;
import com.jeecms.cms.manager.main.CxjMng;
import com.jeecms.common.web.session.SessionProvider;
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
    private CxjManageMng cxjManageMng;

    @Autowired
    private SessionProvider session;

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

}
