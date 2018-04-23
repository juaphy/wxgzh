package com.jeecms.cms.manager.main.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.entity.main.wssb.TSharePost;
import com.jeecms.cms.entity.main.wssb.TWebhallPost;
import com.jeecms.cms.manager.main.CallInterfaceMng;
import com.jeecms.cms.manager.main.MatterService;
import com.jeecms.cms.rest2.entity.SxClxx;
import com.jeecms.core.entity.CmsUser;

import net.sf.json.JSONArray;

@Service
@Transactional
public class MatterServiceImpl implements MatterService {

    @Autowired
    private CallInterfaceMng callInterfaceMng;

    public String getClXml(String sxid,String groupid) {
        String xml = callInterfaceMng.findSxClxx4XmlString(sxid);
        return xml == null? "" : xml;
    }

    @Override
    public String getSxClxx4Json(String sxid,String groupid) {
        List<SxClxx> sxClxxList = callInterfaceMng.findSxClxxList(sxid);
        String xml = null;
        if (sxClxxList != null) {
            xml = JSONArray.fromObject(sxClxxList).toString();
        }
        return xml;
    }

    @Override
    public String getSxClxx4Xml(String sxid,String groupid) {
        String xml = callInterfaceMng.findSxClxxList4Xml(sxid);
        return xml;
    }

    public String getClXml(String bsid) throws Exception{
        String xml = "";
        return xml;
    }

    public String getFormInfoXml(String sxid, String groupid) {
        String xml = "";
        return xml;
    }

    public String getFormInfoXml(String bsid) throws Exception{
        String xml = "";
        return xml;
    }

    public String getGroupXml(String sxid) {
        String xml = "";
        return xml;
    }

    public String getFormXml(String formid){
        String xml = "";
        return xml;
    }
    
    @SuppressWarnings("rawtypes")
    public String getPostInfo(String sxid) throws Exception{
        List infoList = new ArrayList();
        TSharePost post = new TSharePost();
        if(infoList.size()>0) post = (TSharePost)infoList.get(0);
        StringBuffer fb = new StringBuffer();
        fb.append("<root>");
        fb.append("<spsljg_dh>"+(post.getSpsljg_dh()==null?"":post.getSpsljg_dh())+"</spsljg_dh>");
        fb.append("<spsljg_dw>"+(post.getSpsljg_dw()==null?"":post.getSpsljg_dw())+"</spsljg_dw>");
        fb.append("<spsljg_dz>"+(post.getSpsljg_dz()==null?"":post.getSpsljg_dz())+"</spsljg_dz>");
        fb.append("<spsljg_yb>"+(post.getSpsljg_yb()==null?"":post.getSpsljg_yb())+"</spsljg_yb>");
        fb.append("<spsljg_id>"+(post.getId()==null?"":post.getId())+"</spsljg_id>");
        fb.append("</root>");
        return fb.toString();
    }
    
    @SuppressWarnings("rawtypes")
    public String getPostInfo(String sxid, CmsUser user) throws Exception{
        List infoList = new ArrayList();
        TSharePost post = new TSharePost();
        if(infoList.size()>0) post = (TSharePost)infoList.get(0);
        StringBuffer fb = new StringBuffer();
        fb.append("<root>");
        fb.append("<spsljg_dh>"+(post.getSpsljg_dh()==null?"":post.getSpsljg_dh())+"</spsljg_dh>");
        fb.append("<spsljg_dw>"+(post.getSpsljg_dw()==null?"":post.getSpsljg_dw())+"</spsljg_dw>");
        fb.append("<spsljg_dz>"+(post.getSpsljg_dz()==null?"":post.getSpsljg_dz())+"</spsljg_dz>");
        fb.append("<spsljg_yb>"+(post.getSpsljg_yb()==null?"":post.getSpsljg_yb())+"</spsljg_yb>");
        fb.append("<spsljg_id>"+(post.getId()==null?"":post.getId())+"</spsljg_id>");
        fb.append("<app_xm>"+user.getRealname()+"</app_xm>");
        fb.append("<app_dw>"+user.getIntro()+"</app_dw>");
        if(user.getUserInfoMation()!=null){
            if(user.getUserInfoMation().getUseraddress()!=null&&!user.getUserInfoMation().getUseraddress().equals("")){
                fb.append("<app_dz>"+user.getUserInfoMation().getUseraddress()+"</app_dz>");
            }else{
                fb.append("<app_dz></app_dz>");
            }
            if(user.getUserInfoMation().getUserindicia()!=null&&!user.getUserInfoMation().getUserindicia().equals("")){
                fb.append("<app_yb>"+user.getUserInfoMation().getUserindicia()+"</app_yb>");
            }else{
                fb.append("<app_yb></app_yb>");
            }
        }else if(user.getIncInfoMation()!=null){
            if(user.getIncInfoMation().getIncaddr()!=null&&!user.getIncInfoMation().getIncaddr().equals("")){
                fb.append("<app_dz>"+user.getIncInfoMation().getIncaddr()+"</app_dz>");
            }else{
                fb.append("<app_dz></app_dz>");
            }
            if(user.getIncInfoMation().getIncindicia()!=null&&!user.getIncInfoMation().getIncindicia().equals("")){
                fb.append("<app_yb>"+user.getIncInfoMation().getIncindicia()+"</app_yb>");
            }else{
                fb.append("<app_yb></app_yb>");
            }
        }else{
            fb.append("<app_dz></app_dz>");
            fb.append("<app_yb></app_yb>");
        }
        fb.append("<app_dh>"+user.getPhone()+"</app_dh>");
        fb.append("<app_sj>"+user.getMobile()+"</app_sj>");
        fb.append("</root>");
        return fb.toString();
    }
    
    @SuppressWarnings("rawtypes")
    public String getPostInsInfo(String bsnum) throws Exception{
        List infoList = new ArrayList();
        TWebhallPost post = new TWebhallPost();
        if(infoList.size()>0) {
            post = (TWebhallPost)infoList.get(0);
            return post.getMaterial();
        }else{
            return "<MSG></MSG>";
        }
    }

    public String getFormInfoHtmlXml(String sxid, String groupid) {
        return "";
    }

    @Override
    public String getFormInfoHtmlXml(String sxid,String groupid,String userid) {
        String xml = "";
        return xml;
    }

    @Override
    public String getFormInfoHtmlXml(String bsid) throws Exception {
        String xml = "";
        return xml;
    }
    
    public String getFormInfoHtmlData(String sxid,String formid,String formver,String groupid) {
        String xml = "";
        return xml;
    }

}
