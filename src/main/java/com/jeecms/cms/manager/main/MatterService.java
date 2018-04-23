package com.jeecms.cms.manager.main;

import com.jeecms.core.entity.CmsUser;

/**
 * 通过接口获取网上申报-材料信息
 * @author swc 2018-04-17
 *
 */
public interface MatterService {

    /**
     * 根据事项id和分组id得到相关的材料信息列表xml文档
     * @param sxid（事项id）
     * @param groupid（分组id）
     * @return
     */
    public String getClXml(String sxid,String groupid);

    /**
     * 根据事项id和分组id得到相关的材料信息列表JSON字符串
     * @param sxid（事项id）
     * @param groupid（分组id）
     * @return
     */
    public String getSxClxx4Json(String sxid,String groupid);

    /**
     * 根据事项id和分组id得到相关的材料信息列表xml文档
     * @param sxid（事项id）
     * @param groupid（分组id）
     * @return
     */
    public String getSxClxx4Xml(String sxid,String groupid);

    /**
     * 根据业务id得到相关的材料信息列表xml文档
     * @param sxid（事项id）
     * @return
     */
    public String getClXml(String bsid) throws Exception;

    /**
     * 根据事项id得到对应的分组信息的xml
     * @param bsid（事项id）
     * @return
     */
    public String getGroupXml(String sxid);

    /**
     * 根据事项id和分组id得到对应的表单信息列表xml
     * @param bsid（事项id）
     * @param groupid（分组id）
     * @return
     */
    public String getFormInfoXml(String bsid) throws Exception;

    /**
     * 根据业务id得到对应的表单信息列表xml
     * @param sxid（事项id）
     * @return
     */
    public String getFormInfoXml(String sxid,String groupid);

    public String getFormXml(String formid);

    /**
     * 获取事项对应速递业务的相关信息；
     * 返回信息xml
     */
    public String getPostInfo(String sxid) throws Exception;

    public String getPostInfo(String sxid, CmsUser user) throws Exception;

    public String getPostInsInfo(String bsnum) throws Exception;

    /**
     * 根据业务id得到对应的表单信息列表xml
     * @param sxid（事项id）
     * @return
     */
    public String getFormInfoHtmlXml(String sxid,String groupid);

    public String getFormInfoHtmlXml(String sxid,String groupid,String userid);

    public String getFormInfoHtmlXml(String bsid) throws Exception;

    public String getFormInfoHtmlData(String sxid,String formid,String formver,String groupid);

}
