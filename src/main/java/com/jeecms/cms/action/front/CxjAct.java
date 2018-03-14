package com.jeecms.cms.action.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jeecms.cms.entity.main.Content;
import com.jeecms.cms.manager.assist.CmsKeywordMng;
import com.jeecms.cms.manager.main.ContentMng;
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

    /**
     * 中心简介
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

}
