package com.jeecms.cms.action.wxgzh.wssb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.jeecms.cms.Constants;
import com.jeecms.cms.JsonUtil;
import com.jeecms.cms.manager.main.CallInterfaceMng;
import com.jeecms.cms.manager.main.MatterService;
import com.jeecms.cms.rest2.common.JaxbTool;
import com.jeecms.cms.rest2.entity.BaseForm;
import com.jeecms.cms.rest2.entity.BsznInfo;
import com.jeecms.cms.rest2.entity.Business;
import com.jeecms.cms.rest2.entity.BusinessInfo;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.PermissionItemInfo;
import com.jeecms.cms.rest2.entity.SxClxxUploadInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.cms.rest2.impl.CallRestMngImpl;
import com.jeecms.common.util.StringUtils;
import com.jeecms.core.entity.CmsSite;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.web.util.CmsUtils;
import com.jeecms.core.web.util.FrontUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
 * @author VAIO
 * 2018-03-28
 */
@RequestMapping("/wssb")
@Controller
public class WssbAct extends MyAct {

    @Autowired
    private CallInterfaceMng callInterfaceMng;

    @Autowired
    private MatterService matterService;

    // 跳到首页
    @RequestMapping("/index.jspx")
    public String wssbIndex(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }

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
        String areaId = request.getParameter("areaId");
        if (StringUtils.isEmpty(areaId) || StringUtils.isEmpty(deptId)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }

        // 获取部门事项列表
        List<SxInfo> sxInfos = callInterfaceMng.findSxlbByDeptid(deptId);
        model.put("sxInfos", sxInfos);
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "bsfw_bmsxlb");
    }

    @RequestMapping("/bszn/{sxid}.jspx")
    public String bsznInfo(@PathVariable String sxid, HttpServletRequest request, HttpServletResponse response,
            ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        String deptId = request.getParameter("deptId");
        if (StringUtils.isEmpty(sxid) || StringUtils.isEmpty(areaId)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        model.put("deptId", deptId);

        // 获取办事指南信息
        BsznInfo bszn = callInterfaceMng.findBsznInfo(sxid);
        if (bszn != null) {
            model.put("permissionitem", bszn.getPermissionItemInfo());
        }
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "bsfw_bszn");
    }

    // 跳到进度查询页面
    @RequestMapping("/jdcx.jspx")
    public String jdcx(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }

        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "bsfw_bjcx");
    }

    // 跳到进度查询结果页面
    @RequestMapping("/jdcx/result.jspx")
    public String jdcxResult(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        String bsnum = request.getParameter("bid");
        String appname = request.getParameter("bsrxm");
        BusinessInfo bsinfo = callInterfaceMng.findBusiInfo(bsnum, appname);
        model.put("bsinfo", bsinfo);
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "bsfw_bjcx_result");
    }

    // 跳到办事地图页面
    @RequestMapping("/bsmap.jspx")
    public String bsmap(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws JSONException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        return FrontUtils.getTplPath(site.getMobileSolutionPath(), Constants.WSSB_INDEX, "zwfw_map");
    }

    // 申报页面
    @RequestMapping(value = "/chatwssb.jspx")
    public String chatwssb(HttpServletRequest request,
            HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user = CmsUtils.getUser(request);
        FrontUtils.frontData(request, model, site);
        String areaId = request.getParameter("areaId");
        if (!isProcZwzxConfig(areaId, model)) {
            return FrontUtils.pageNotFound(request, response, model);
        }
        String sxid=request.getParameter("sxid");
        String typecss=request.getParameter("typecss");
        model.put("typecss", typecss);

        if(user==null||sxid==null){
            return "redirect:/wssb/logindex.jspx?areaId=" + areaId + "&isWssb=true&goto=/wssb/chatwssb.jspx&sxid=" + sxid;
        }
        PermissionItemInfo item = null;

        // 获取办事指南信息
        BsznInfo bszn = callInterfaceMng.findBsznInfo(sxid);
        if (bszn != null) {
            item = bszn.getPermissionItemInfo();
        }
        if (item != null&&user!=null) {
            // String account = user.getUsername();
            String usercode="";
            String username="";
            String phone="";
            if(user.getUserType()!=null&&"1".equals(user.getUserType())){
                usercode=user.getUserInfoMation().getUserpid();
                username=user.getUserInfoMation().getUsername();
                phone=user.getMobile()!=null?user.getMobile():"";
            }else if(user.getUserType()!=null&&"2".equals(user.getUserType())){
                usercode=user.getIncInfoMation().getIncpid();
                //username=user.getIncInfoMation().getAgename();
                username=user.getIncInfoMation().getIncname();
                phone=user.getMobile()!=null?user.getMobile():user.getIncInfoMation().getAgemobile();
                if(phone==null){
                    phone="";
                }
            }
            
            model.addAttribute("token", "");
            model.addAttribute("Sxmc", item.getSxzxname());
            model.addAttribute("username", username);
            model.addAttribute("sxId", sxid);
            model.addAttribute("userid", user.getId());
            model.addAttribute("usercode",usercode);
            model.addAttribute("phone",phone);
        } 
        return FrontUtils.getTplPath(request, site.getMobileSolutionPath(), Constants.WSSB_INDEX, "tpl.ysgzmobile");
    }

    // 填写申报信息-跳到申请材料页面
    @RequestMapping(value = "/sqcl.jspx")
    public String sqclpage(String token,String sxId,String Sxmc,String username, HttpServletRequest request, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        CmsUser user =CmsUtils.getUser(request);
        try {
            if(user==null){
                return "redirect:/wssb/logindex.jspx";
            }
            String usercode="";
            if(user.getUserType()!=null&&"1".equals(user.getUserType())){
                usercode=user.getUserInfoMation().getUserpid();
                username=user.getUserInfoMation().getUsername();
            }else if(user.getUserType()!=null&&"2".equals(user.getUserType())){
                usercode=user.getIncInfoMation().getIncpid();
                //username=user.getIncInfoMation().getAgename();
                username=user.getIncInfoMation().getIncname();
            }
            String baseForm=request.getParameter("baseForm");
            if(baseForm!=null&&!baseForm.equals("")){
                System.out.println(baseForm);
                baseForm=baseForm.replaceAll("“", "\"");
                baseForm=baseForm.replaceAll("：", ":");
                System.out.println(baseForm);
            }
            //System.out.println(BASE64Helper.decode(baseForm));
            FrontUtils.frontData(request, model, site);
            model.addAttribute("token", token!=null?token:"");
            model.addAttribute("Sxmc", Sxmc!=null&&!Sxmc.equals("")?Sxmc:"");
            model.addAttribute("sxId", sxId!=null?sxId:"");
            model.addAttribute("usercode", usercode);
            model.addAttribute("param", baseForm!=null?baseForm:"");
            setFrontProperties(model, request);
            System.out.println(site.getSolutionPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FrontUtils.getTplPath(request, site.getMobileSolutionPath(), Constants.WSSB_INDEX, "tpl.ysgzsqcl");
    }

    // 获取申请材料信息
    @RequestMapping(value = "/cl.jspx")
    public void wssbCl(String sxid, String groupid, String bsid, HttpServletRequest request,
            HttpServletResponse response, ModelMap model) throws Exception {
        String xml = "";

        if (sxid != null && !"".equals(sxid)) {
            xml = matterService.getSxClxx4Xml(sxid, groupid);
        } else if (bsid != null && !"".equals(bsid)) {
            xml = matterService.getClXml(bsid);
        }
        //暂存
        /*xml="<cllist>"
                + "<cl><clid>52000020150116150529001129</clid><clmc>由具备相应工程咨询资格的机构编制项目申请报告，包括以下内容：（一）项目单位情况；（二）拟建项目情况；（三）资源利用和生态环境影响分析；（四）经济和社会影响分析。</clmc><dzhyq>5,3</dzhyq><gid>20170425134426156N</gid><baseinfo>null</baseinfo><formver> </formver><uid>577943</uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype>3</ctype><templateid></templateid><templateName></templateName><bz></bz><cclid>52000020150116150529001129</cclid><fname>由具备相应工程咨询资格的机构编制项目申请报告，包括以下内容：（一）项目单位情况；（二）拟建项目情况；（三）资源利用和生态环境影响分析；（四）经济和社会影响分析。</fname><orinum>null</orinum><copynum>null</copynum><pid></pid><clbh>#CLBH#</clbh><clyq>纸质原件5份</clyq><filelist></filelist><xid></xid><fid></fid></cl>"
                + "<cl><clid>52000020150116150619001135</clid><clmc>城乡规划行政主管部门出具的选址意见书（仅指以划拨方式提供国有土地使用权的项目）；</clmc><dzhyq>5,3</dzhyq><gid>20170425134426157N</gid><baseinfo>null</baseinfo><formver> </formver><uid>577943</uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype>3</ctype><templateid></templateid><templateName></templateName><bz></bz><cclid>52000020150116150619001135</cclid><fname>城乡规划行政主管部门出具的选址意见书（仅指以划拨方式提供国有土地使用权的项目）；</fname><orinum>null</orinum><copynum>null</copynum><pid></pid><clbh>#CLBH#</clbh><clyq>纸质原件1份，复印件4份。</clyq><filelist></filelist><xid></xid><fid></fid></cl>"
                + "<cl><clid>52000020150116150815001149</clid><clmc>根据有关法律法规应提交的其他文件。</clmc><dzhyq>5,3</dzhyq><gid>20170425134426158N</gid><baseinfo>null</baseinfo><formver> </formver><uid>577943</uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype>3</ctype><templateid></templateid><templateName></templateName><bz></bz><cclid>52000020150116150815001149</cclid><fname>根据有关法律法规应提交的其他文件。</fname><orinum>null</orinum><copynum>null</copynum><pid></pid><clbh>#CLBH#</clbh><clyq>纸质原件1份，纸质复印件4份。</clyq><filelist></filelist><xid></xid><fid></fid></cl>"
                + "<cl><clid>52000020150116150802001147</clid><clmc>项目单位对项目申请报告内容和附属文件真实性负责的承诺；</clmc><dzhyq>5,3</dzhyq><gid>20170425134426159N</gid><baseinfo>null</baseinfo><formver> </formver><uid>577943</uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype>3</ctype><templateid></templateid><templateName></templateName><bz></bz><cclid>52000020150116150802001147</cclid><fname>项目单位对项目申请报告内容和附属文件真实性负责的承诺；</fname><orinum>null</orinum><copynum>null</copynum><pid></pid><clbh>#CLBH#</clbh><clyq>纸质原件1份，复印件4份。</clyq><filelist></filelist><xid></xid><fid></fid></cl>"
                + "<cl><clid>52000020150116150628001137</clid><clmc>国土资源行政主管部门出具的用地预审意见（不涉及新增用地，在已批准的建设用地范围内进行改扩建的项目，可以不进行用地预审）；</clmc><dzhyq>5,3</dzhyq><gid>20170425134426160N</gid><baseinfo>null</baseinfo><formver> </formver><uid>577943</uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype>3</ctype><templateid></templateid><templateName></templateName><bz></bz><cclid>52000020150116150628001137</cclid><fname>国土资源行政主管部门出具的用地预审意见（不涉及新增用地，在已批准的建设用地范围内进行改扩建的项目，可以不进行用地预审）；</fname><orinum>null</orinum><copynum>null</copynum><pid></pid><clbh>#CLBH#</clbh><clyq>纸质原件1份，复印件4份。</clyq><filelist></filelist><xid></xid><fid></fid></cl>"
                + "<length>5</length>"
                + "<sxname><![CDATA[涉及开荒的农业项目]]></sxname>"
                + "</cllist>";*/
        //初始化
        /*xml="<cllist>"
                + "<cl><clid>52000020150116150529001129</clid><clmc>由具备相应工程咨询资格的机构编制项目申请报告，包括以下内容：（一）项目单位情况；（二）拟建项目情况；（三）资源利用和生态环境影响分析；（四）经济和社会影响分析。</clmc><dzhyq>3</dzhyq><xid></xid><gid>20170425140639107N</gid><baseinfo></baseinfo><formver> </formver><fid></fid><uid></uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype></ctype><templateid></templateid><templateName></templateName><bz> </bz><cclid>52000020150116150529001129</cclid><fname>由具备相应工程咨询资格的机构编制项目申请报告，包括以下内容：（一）项目单位情况；（二）拟建项目情况；（三）资源利用和生态环境影响分析；（四）经济和社会影响分析。</fname><orinum></orinum><copynum></copynum><pid></pid><clbh>#CLBH#</clbh><sfby>1</sfby><clyq>纸质原件5份</clyq></cl>"
                + "<cl><clid>52000020150116150619001135</clid><clmc>城乡规划行政主管部门出具的选址意见书（仅指以划拨方式提供国有土地使用权的项目）；</clmc><dzhyq>3</dzhyq><xid></xid><gid>20170425140639108N</gid><baseinfo></baseinfo><formver> </formver><fid></fid><uid></uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype></ctype><templateid></templateid><templateName></templateName><bz> </bz><cclid>52000020150116150619001135</cclid><fname>城乡规划行政主管部门出具的选址意见书（仅指以划拨方式提供国有土地使用权的项目）；</fname><orinum></orinum><copynum></copynum><pid></pid><clbh>#CLBH#</clbh><sfby>1</sfby><clyq>纸质原件1份，复印件4份。</clyq></cl>"
                + "<cl><clid>52000020150116150815001149</clid><clmc>根据有关法律法规应提交的其他文件。</clmc><dzhyq>3</dzhyq><xid></xid><gid>20170425140639109N</gid><baseinfo></baseinfo><formver> </formver><fid></fid><uid></uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype></ctype><templateid></templateid><templateName></templateName><bz> </bz><cclid>52000020150116150815001149</cclid><fname>根据有关法律法规应提交的其他文件。</fname><orinum></orinum><copynum></copynum><pid></pid><clbh>#CLBH#</clbh><sfby>1</sfby><clyq>纸质原件1份，纸质复印件4份。</clyq></cl>"
                + "<cl><clid>52000020150116150802001147</clid><clmc>项目单位对项目申请报告内容和附属文件真实性负责的承诺；</clmc><dzhyq>3</dzhyq><xid></xid><gid>20170425140639110N</gid><baseinfo></baseinfo><formver> </formver><fid></fid><uid></uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype></ctype><templateid></templateid><templateName></templateName><bz> </bz><cclid>52000020150116150802001147</cclid><fname>项目单位对项目申请报告内容和附属文件真实性负责的承诺；</fname><orinum></orinum><copynum></copynum><pid></pid><clbh>#CLBH#</clbh><sfby>1</sfby><clyq>纸质原件1份，复印件4份。</clyq></cl>"
                + "<cl><clid>52000020150116150628001137</clid><clmc>国土资源行政主管部门出具的用地预审意见（不涉及新增用地，在已批准的建设用地范围内进行改扩建的项目，可以不进行用地预审）；</clmc><dzhyq>3</dzhyq><xid></xid><gid>20170425140639111N</gid><baseinfo></baseinfo><formver> </formver><fid></fid><uid></uid><zt>0</zt><sxid>20140826104658016544</sxid><ctype></ctype><templateid></templateid><templateName></templateName><bz> </bz><cclid>52000020150116150628001137</cclid><fname>国土资源行政主管部门出具的用地预审意见（不涉及新增用地，在已批准的建设用地范围内进行改扩建的项目，可以不进行用地预审）；</fname><orinum></orinum><copynum></copynum><pid></pid><clbh>#CLBH#</clbh><sfby>1</sfby><clyq>纸质原件1份，复印件4份。</clyq></cl>"
                + "<length>5</length></cllist>";*/
        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
    //  System.out.println(xml);
        out.print(xml);
        out.flush();
        out.close();
    }

    // 提交申报
    @ResponseBody
    @RequestMapping(value = "/toShenBao.jspx", method = RequestMethod.POST)
    public String toshenBao(String sxId,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        String baseInfo = request.getParameter("baseinfo"); // 申请人基本信息
        String cllist = request.getParameter("materials"); // 材料内容
        String baseForm = request.getParameter("baseForm"); // 基础表单信息
        String status = request.getParameter("status");
        CmsUser cmsUser = CmsUtils.getUser(request);

        // 封装参数
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("BUSINESS", baseInfo);
        params.put("BASEFORM", baseForm);
        params.put("MATERIALS", cllist);
        return "";
    }

    // 上传附件到网厅
    @ResponseBody
    @RequestMapping("/uploadFileToWt.jspx")
    public String uploadFileToWt(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CmsUser cmsUser = CmsUtils.getUser(request);
        String fileName = request.getParameter("filename");
        String sxid = request.getParameter("sxid");
        Map<String, String> param = new HashMap<String, String>();
        param.put("filename", fileName);
        param.put("sxid", sxid);
        param.put("userid", String.valueOf(cmsUser.getId()));
        SxClxxUploadInfo info = callInterfaceMng.uploadSxcl(request, param);

        /*PrintWriter out = response.getWriter();
        out.print(JsonUtil.toJson(info));
        out.flush();
        out.close();*/
        // response.getWriter().write(JsonUtil.toJson(info));
        if (info != null) {
            info.setStatus("fileid");
            info.setUploadPath(CallRestMngImpl.WTIOHTTP_ADRR);
            return JsonUtil.toJson(info);
        }
        return "";
    }
    @RequestMapping(value = "/ts.jspx", method = RequestMethod.GET)
    public String goNext(String bsnum,HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException {
        CmsSite site = CmsUtils.getSite(request);
        FrontUtils.frontData(request, model, site);
        model.addAttribute("bsnum", bsnum);
        model.addAttribute("type", "1");
        System.out.println(site.getSolutionPath());
        return FrontUtils.getTplPath(request, site.getMobileSolutionPath(), Constants.WSSB_INDEX, "tpl.h5tspage");
    }

}
