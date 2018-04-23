package com.jeecms.cms.manager.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.jeecms.cms.rest2.entity.BsznInfo;
import com.jeecms.cms.rest2.entity.BusinessInfo;
import com.jeecms.cms.rest2.entity.DeptInfo;
import com.jeecms.cms.rest2.entity.SxClxx;
import com.jeecms.cms.rest2.entity.SxClxxUploadInfo;
import com.jeecms.cms.rest2.entity.SxInfo;
import com.jeecms.cms.rest2.entity.WUser;
import com.jeecms.cms.rest2.entity.WssbBusinessinfo;

/**
 * 调用网厅接口
 * 
 */
public interface CallInterfaceMng {

    /**
     * 根据区划id获取部门列表（调用接口）
     * @param areaId
     * @param isZsbm 是否直属部门：6区县级 、7乡镇级、8村居
     * @return
     */
    public List<DeptInfo> findDeptListByAreaid(String areaId, String isZsbm);

    /**
     * 根据部门id获取事项列表（调接口）
     * @param deptid
     * @return
     */
    public List<SxInfo> findSxlbByDeptid(String deptid);

    /**
     * 根据业务id获取业务相关信息（调接口）
     * @param bsnum 业务编号
     * @param appname 申请人名称
     * @return
     */
    public BusinessInfo findBusiInfo(String bsnum, String appname);

    /**
     * 根据事项id获取办事指南相关信息
     * 
     * @param sxid
     * @return
     * <pre>
     *  permissionItemInfo 事项详情
     *  sxSqclList 申请材料列表
     *  sxSdyjList 设定依据列表
     *  sxSlhjList 受理环节列表
     *  sxTshjList 特殊环节列表
     * </pre>
     */
    public BsznInfo findBsznInfo(String sxid);

    /**
     * 根据事项id获取事项材料信息（原值返回）
     * @param sxid
     * @return
     */
    public String findSxClxx4XmlString(String sxid);

    /**
     * 根据事项id获取事项材料信息
     * @param sxid
     * @return
     */
    public List<SxClxx> findSxClxxList(String sxid);

    /**
     * 根据事项id获取事项材料信息
     * @param sxid
     * @return xml结构的String
     */
    public String findSxClxxList4Xml(String sxid);

    /**
     * 将事项材料上传到远程服务器，并返回文件id等信息
     * @param request
     * @param param
     * @return
     */
    public SxClxxUploadInfo uploadSxcl(HttpServletRequest request, Map<String, String> param);

    /**
     * 提交网上申报信息
     * @param request 从request中获取以下参数的相关信息
     * <pre>
     * 参数名             数据类型    是否必须       描述                    数据格式         示例
     * BUSINESS  String  必须             事项编号            xml字符串
     * BASEFORM  String  必须             基本表单信息    xml字符串
     * MATERIALS String  必须             材料内容           xml字符串
     * PAPER     String  非必须         邮政速递递交    xml字符串
     * RESULT    String  非必须         邮政结果领取    xml字符串
     * token     String  必须             用户会话验证    字符串             2C4FDB6D-9F78-40A7-97F6-8AD71F6AD5A8
     * </pre>
     * @return
     */
    public WssbBusinessinfo submitWssb(Map<String, Object> params);

    /**
     * 调用网厅接口进行登录
     * @param params
     * @return
     */
    public WUser login4Wt(Map<String, Object> params);

}