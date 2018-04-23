package com.jeecms.cms.action.wxgzh.wssb;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.jeecms.cms.JsonUtil;
import com.jeecms.cms.dao.main.DsfxtConfigService;
import com.jeecms.cms.entity.main.cxj.TCxjZwzxconfig;
import com.jeecms.cms.entity.main.wssb.TSysDsfxtConfig;
import com.jeecms.cms.entity.main.wssb.TWebHallIncInforMation;
import com.jeecms.cms.entity.main.wssb.TWebHallUserInforMation;
import com.jeecms.cms.manager.main.WxgzhWssbMng;
import com.jeecms.common.util.HttpRequest;
import com.jeecms.common.web.CookieUtils;
import com.jeecms.core.entity.CmsUser;
import com.jeecms.core.entity.CmsUserExt;
import com.jeecms.core.manager.CmsUserMng;

import net.sf.json.JSONObject;

/**
 * 自定义Action父类方法
 * <pre>
 *<strong>概要：</strong>
 *      提供一些共通的处理方法，以减少Action代码的冗余
 * </pre>
 * @author swc 2018-04-02
 *
 */
public class MyAct {

    @Autowired
    private WxgzhWssbMng wxgzhWssbMng;

    @Autowired
    private DsfxtConfigService dsfxtconfigservice;

    public static final String COOKIE_ERROR_REMAINING = "_error_remaining";

    @Autowired
    private CmsUserMng cmsUserMng;

    // public static final String WTIOHTTP_ADRR = "http://127.0.0.1:8083";

    /**
     * 判断对应区划id的系统配置是否存在
     * @param areaId
     * @param model
     * @return 如果系统配置不存在，则返回false；如果存在，则将配置信息、区划id设置到model中
     */
    protected boolean isProcZwzxConfig(String areaId, ModelMap model) {
        if (isEmpty(areaId)) {
            return false;
        }
        TCxjZwzxconfig zwzxConfig = wxgzhWssbMng.findZwzxconfig(areaId);
        if (zwzxConfig == null) {
            return false;
        }
        model.put("zwzxConfig", zwzxConfig);
        model.put("areaId", areaId);
        return true;
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return null或者空串，则返回true
     */
    protected boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 判断字符串是否为空
     * @param str
     * @return null或者空串，或者值为"NULL"、"null"，则返回true
     */
    protected boolean isEmptyNull(String str) {
        return str == null || "".equals(str) || "NULL".equals(str) || "null".equals(str);
    }

    /**
     * 加载表的方法获取外部配置信息（如浪潮-网厅用户注册、登录服务地址）
     * @param model
     * @param request
     */
    protected void setFrontProperties(ModelMap model, HttpServletRequest request) {
        List<TSysDsfxtConfig> urlConfigList = dsfxtconfigservice.getDsfxtConfigList();
        HttpRequest.setPzPathNew(model, request,urlConfigList);
    }

    @SuppressWarnings("unchecked")
    protected String dataXml(String baseFormXml){
        Document baseFormdocument;
        Element element=null;
        try {
             baseFormdocument = DocumentHelper.parseText(baseFormXml);
             Element ele = baseFormdocument.getRootElement();
             List<Element> el = ele.elements("data");
             if(el != null && el.size() > 0){
                   element = el.get(0);
                   System.out.println(element.asXML());
                   return element.asXML();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return null;
    }

    protected Map<String,Object> getSecureAttrs(Map<String,String> map,String url) throws Exception{
        String existParam=HttpRequest.getParam(map);
        String  ret=HttpRequest.sendPost(url, existParam);
        System.out.println(ret);
        Map<String,Object> M=null;
        JSONObject json=JSONObject.fromObject(ret);
        String msg=json.get("user")!=null?json.getString("user"):null;
        String state=json.get("state")!=null?json.getString("state"):null;
        if(msg!=null&&!"null".equalsIgnoreCase(msg)){
            M=JsonUtil.jsonToMap(msg);
            M.put("state", state);
        }
        return M;
    }

    /**
     * 保存用户信息
     * @param secureAttrs
     * @param ip
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unused")
    protected CmsUser saveUser(Map<String,Object> secureAttrs,String ip) throws Exception {

        String USER_TYPE =secureAttrs.get("USER_TYPE")!=null?secureAttrs.get("USER_TYPE").toString():"";
        String EMAIL=secureAttrs.get("EMAIL")!=null?secureAttrs.get("EMAIL").toString():"";
        String LOGIN_NAME=secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";
        String LOGIN_PWD=secureAttrs.get("LOGIN_PWD")!=null?secureAttrs.get("LOGIN_PWD").toString():"";
        TWebHallUserInforMation userinfo = null;
        TWebHallIncInforMation incinfo = null;
        CmsUserExt userExt = new CmsUserExt();
        if ("1".equals(USER_TYPE)) {
            USER_TYPE =secureAttrs.get("USER_TYPE")!=null?secureAttrs.get("USER_TYPE").toString():"";
            String USER_ID=secureAttrs.get("USER_ID")!=null?secureAttrs.get("USER_ID").toString():"";
             LOGIN_NAME=secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";
             LOGIN_PWD=secureAttrs.get("LOGIN_PWD")!=null?secureAttrs.get("LOGIN_PWD").toString():"";
            String CREATE_TIME=secureAttrs.get("CREATE_TIME")!=null?secureAttrs.get("CREATE_TIME").toString():"";
            String USER_NAME=secureAttrs.get("USER_NAME")!=null?secureAttrs.get("USER_NAME").toString():"";
            String MOBILE=secureAttrs.get("MOBILE")!=null?secureAttrs.get("MOBILE").toString():"";
            String CARD_NO=secureAttrs.get("CARD_NO")!=null?secureAttrs.get("CARD_NO").toString():"";
            String STATUS=secureAttrs.get("STATUS")!=null?secureAttrs.get("STATUS").toString():"";
            String SORT_ORDER=secureAttrs.get("SORT_ORDER")!=null?secureAttrs.get("SORT_ORDER").toString():"";
            String ENCRYPTIONTY=secureAttrs.get("ENCRYPTIONTY")!=null?secureAttrs.get("ENCRYPTIONTY").toString():"";
            String REAL_AUTH=secureAttrs.get("REAL_AUTH")!=null?secureAttrs.get("REAL_AUTH").toString():"0";
            String SEX=secureAttrs.get("SEX")!=null?secureAttrs.get("SEX").toString():"";
            String BIRTHDAY=secureAttrs.get("BIRTHDAY")!=null?secureAttrs.get("BIRTHDAY").toString():"";
            String BANK_NO=secureAttrs.get("BANK_NO")!=null?secureAttrs.get("BANK_NO").toString():"";
             EMAIL=secureAttrs.get("EMAIL")!=null?secureAttrs.get("EMAIL").toString():"";
            String ALIPAY_NO=secureAttrs.get("ALIPAY_NO")!=null?secureAttrs.get("ALIPAY_NO").toString():"";
            String CREATOR=secureAttrs.get("CREATOR")!=null?secureAttrs.get("CREATOR").toString():"";
            String SOCIAL_CARD=secureAttrs.get("SOCIAL_CARD")!=null?secureAttrs.get("SOCIAL_CARD").toString():"";
            String FUND_CARD=secureAttrs.get("FUND_CARD")!=null?secureAttrs.get("FUND_CARD").toString():"";
            String CAR_CARD=secureAttrs.get("CAR_CARD")!=null?secureAttrs.get("CAR_CARD").toString():"";
            String REGISTER_TYPE=secureAttrs.get("REGISTER_TYPE")!=null?secureAttrs.get("REGISTER_TYPE").toString():"";
            //String ADDRESS=secureAttrs.get("ADDRESS")!=null?secureAttrs.get("ADDRESS").toString():"";
            String ADDRESS =secureAttrs.get("PROVINCE")!=null?secureAttrs.get("PROVINCE").toString():"";
                   ADDRESS+=secureAttrs.get("CITY")!=null?secureAttrs.get("CITY").toString():"";
                   ADDRESS+=secureAttrs.get("DISTRICT")!=null?secureAttrs.get("DISTRICT").toString():"";
                   ADDRESS+=secureAttrs.get("STREET")!=null?secureAttrs.get("STREET").toString():"";

                   /* 个人用户信息收集 */
            String name = USER_NAME;
            String usergender = SEX;
            String certificatetype = "1";//证件类型   默认设置身份证号码
            String userpid = CARD_NO;
            String userphone = MOBILE;
            String usermobile = MOBILE;
            String useremail = EMAIL;
            String useraddress = ADDRESS; //地址
            String userindicia = EMAIL;//邮编
            String realauth=REAL_AUTH;
            userinfo = new TWebHallUserInforMation();
            //userinfo.setUseruid(Integer.parseInt(USER_ID));
            userinfo.setUsername(name);
            userinfo.setUsergender(usergender);
            userinfo.setCertificatetype(certificatetype);
            userinfo.setUserpid(userpid);
            userinfo.setUserphone(userphone);
            userinfo.setUsermobile(usermobile);
            userinfo.setUseremail(useremail);
            userinfo.setUseraddress(useraddress);
            userinfo.setUserindicia(userindicia);
            userinfo.setUserdate(new Timestamp(System.currentTimeMillis()));
            userinfo.setRealauth(realauth);
            userExt.setRealname(name);
            if(usergender.equals("1")){
                userExt.setGender(true);
            }else if(usergender.equals("0")){
                userExt.setGender(false);
            }else{
                userExt.setGender(null);
            } 
            userExt.setPhone(usermobile);
            userExt.setMobile(userphone);
            userExt.setComefrom(useraddress);
            userExt.setMsn(userpid);
            /* 个人用户信息收集end */
        }else if ("2".equals(USER_TYPE)) {
            /* 企业用户信息收集 */
            String CORPORATE_ID = secureAttrs.get("CORPORATE_ID")!=null?secureAttrs.get("CORPORATE_ID").toString():"";          //法人用户ID
            String CORPORATE_NAME = secureAttrs.get("CORPORATE_NAME")!=null?secureAttrs.get("CORPORATE_NAME").toString():"";    //法人名称
            String CORPORATE_CODE = secureAttrs.get("CORPORATE_CODE")!=null?secureAttrs.get("CORPORATE_CODE").toString():"";    //统一社会信用代码
            String REPRESENTATIVE_NAME = secureAttrs.get("REPRESENTATIVE_NAME")!=null?secureAttrs.get("REPRESENTATIVE_NAME").toString():"";//法定代表人姓名
            String REPRESENTATIVE_CARD_NO = secureAttrs.get("REPRESENTATIVE_CARD_NO")!=null?secureAttrs.get("REPRESENTATIVE_CARD_NO").toString():"";//法定代表人身份证号
             LOGIN_NAME = secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";              //登录账号
             LOGIN_PWD = secureAttrs.get("LOGIN_PWD")!=null?secureAttrs.get("LOGIN_PWD").toString():"";                 //登录密码
            String STATUS = secureAttrs.get("STATUS")!=null?secureAttrs.get("STATUS").toString():"";                            //状态（0:无效 1:有效）
            String CREATE_TIME = secureAttrs.get("CREATE_TIME")!=null?secureAttrs.get("CREATE_TIME").toString():"";             //创建时间
            String SORT_ORDER = secureAttrs.get("SORT_ORDER")!=null?secureAttrs.get("SORT_ORDER").toString():"";                //排序
            String CORPORATE_TYPE = secureAttrs.get("CORPORATE_TYPE")!=null?secureAttrs.get("CORPORATE_TYPE").toString():"";    //法人类型  1：企业法人 2：社团法人 
             EMAIL = secureAttrs.get("EMAIL")!=null?secureAttrs.get("EMAIL").toString():"";                             //邮箱
            String MOBILE = secureAttrs.get("MOBILE")!=null?secureAttrs.get("MOBILE").toString():"";                            //手机号
            String ENCRYPTIONTY = secureAttrs.get("ENCRYPTIONTY")!=null?secureAttrs.get("ENCRYPTIONTY").toString():"";          //密码加密类型 1md5 2 sha-1
            String  CREATOR = secureAttrs.get("CREATOR")!=null?secureAttrs.get("CREATOR").toString():"";                        //创建者ID
            String CORPORATE_PHONE = secureAttrs.get("CORPORATE_PHONE")!=null?secureAttrs.get("CORPORATE_PHONE").toString():""; //联系电话
            String REGISTER_ADDRESS = secureAttrs.get("REGISTER_ADDRESS")!=null?secureAttrs.get("REGISTER_ADDRESS").toString():"";//注册地址
            String REGISTER_AGENCY = secureAttrs.get("REGISTER_AGENCY")!=null?secureAttrs.get("REGISTER_AGENCY").toString():""; //审批机关
            String AGENT_NAME = secureAttrs.get("AGENT_NAME")!=null?secureAttrs.get("AGENT_NAME").toString():"";                //办理人姓名
            String AGENT_MOBILE = secureAttrs.get("AGENT_MOBILE")!=null?secureAttrs.get("AGENT_MOBILE").toString():"";          //办理人手机
            String AGENT_CARD_NO = secureAttrs.get("AGENT_CARD_NO")!=null?secureAttrs.get("AGENT_CARD_NO").toString():"";       //办理人身份证号
            String CORPORATE_PICTURE = secureAttrs.get("CORPORATE_PICTURE")!=null?secureAttrs.get("CORPORATE_PICTURE").toString():"";//法人证照
            String REAL_AUTH = secureAttrs.get("REAL_AUTH")!=null?secureAttrs.get("REAL_AUTH").toString():"0";                  //实名认证类型（以逗号分隔隔） 0 未认证 1 网上证照实名认证 9 实体大厅现场审核验证
            String REGISTER_TYPE = secureAttrs.get("REGISTER_TYPE")!=null?secureAttrs.get("REGISTER_TYPE").toString():"";       //注册来源 0 网上自主注册 1 实体大厅现成注册
            //String ADDRESS = secureAttrs.get("ADDRESS")!=null?secureAttrs.get("ADDRESS").toString():"";                           //联系地址
            
            String ADDRESS =secureAttrs.get("PROVINCE")!=null?secureAttrs.get("PROVINCE").toString():"";
               ADDRESS+=secureAttrs.get("CITY")!=null?secureAttrs.get("CITY").toString():"";
               ADDRESS+=secureAttrs.get("DISTRICT")!=null?secureAttrs.get("DISTRICT").toString():"";
               ADDRESS+=secureAttrs.get("STREET")!=null?secureAttrs.get("STREET").toString():"";

            
            String incname = CORPORATE_NAME;
            String inctype = "5"; //企业类型，1国有，2民营，3外资，4港澳台资，5其他
            String incisnew = "";// 是否办理新设立企业，0否，1是
            String incpermit = CORPORATE_CODE;//营业执照
            String inczzjgdm = CORPORATE_CODE;
            String incdeputy =  REPRESENTATIVE_NAME;
            String incpid = REPRESENTATIVE_CARD_NO;
            String agename = AGENT_NAME;
            String agepid = AGENT_CARD_NO;
            String ageemail = EMAIL;
            String agephone = AGENT_MOBILE;
            String ageindicia = ""; //邮政编码
            String ageaddr = ADDRESS;
            String realauth=REAL_AUTH;
            incinfo = new TWebHallIncInforMation();
            incinfo.setIncname(incname);
            incinfo.setInctype(inctype);
            incinfo.setIncisnew(incisnew);
            incinfo.setIncpermit(incpermit);
            incinfo.setInczzjgdm(inczzjgdm);
            incinfo.setIncdeputy(incdeputy);
            incinfo.setIncpid(incpid);
            incinfo.setAgename(agename);
            incinfo.setAgepid(agepid);
            incinfo.setAgeemail(ageemail);
            incinfo.setAgephone(agephone);
            incinfo.setAgemobile(agephone);
            incinfo.setAgeindicia(ageindicia);
            incinfo.setAgeaddr(ageaddr);
            incinfo.setRealauth(realauth);
            
            incinfo.setInclogindate(new Timestamp(System.currentTimeMillis()));
             
            userExt.setRealname(incname);
            userExt.setPhone(MOBILE);
            userExt.setMobile(MOBILE);
            userExt.setComefrom(REGISTER_ADDRESS);
            userExt.setMsn(incpid);
        }
        CmsUser user=cmsUserMng.registerMemberForTyrz(LOGIN_NAME, EMAIL, LOGIN_PWD, ip, null, userExt, true, null, null, userinfo, incinfo);
        return user;
    }
    @SuppressWarnings("unused")
    protected TWebHallIncInforMation mapToBean(Map<String,Object> secureAttrs,TWebHallIncInforMation incinfo){
        String CORPORATE_ID = secureAttrs.get("CORPORATE_ID")!=null?secureAttrs.get("CORPORATE_ID").toString():"";          //法人用户ID
        String CORPORATE_NAME = secureAttrs.get("CORPORATE_NAME")!=null?secureAttrs.get("CORPORATE_NAME").toString():"";    //法人名称
        String CORPORATE_CODE = secureAttrs.get("CORPORATE_CODE")!=null?secureAttrs.get("CORPORATE_CODE").toString():"";    //统一社会信用代码
        String REPRESENTATIVE_NAME = secureAttrs.get("REPRESENTATIVE_NAME")!=null?secureAttrs.get("REPRESENTATIVE_NAME").toString():"";//法定代表人姓名
        String REPRESENTATIVE_CARD_NO = secureAttrs.get("REPRESENTATIVE_CARD_NO")!=null?secureAttrs.get("REPRESENTATIVE_CARD_NO").toString():"";//法定代表人身份证号
        String LOGIN_NAME = secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";                //登录账号
        String LOGIN_PWD = secureAttrs.get("LOGIN_PWD")!=null?secureAttrs.get("LOGIN_PWD").toString():"";                   //登录密码
        String STATUS = secureAttrs.get("STATUS")!=null?secureAttrs.get("STATUS").toString():"";                            //状态（0:无效 1:有效）
        String CREATE_TIME = secureAttrs.get("CREATE_TIME")!=null?secureAttrs.get("CREATE_TIME").toString():"";             //创建时间
        String SORT_ORDER = secureAttrs.get("SORT_ORDER")!=null?secureAttrs.get("SORT_ORDER").toString():"";                //排序
        String CORPORATE_TYPE = secureAttrs.get("CORPORATE_TYPE")!=null?secureAttrs.get("CORPORATE_TYPE").toString():"";    //法人类型  1：企业法人 2：社团法人 
        String EMAIL = secureAttrs.get("EMAIL")!=null?secureAttrs.get("EMAIL").toString():"";                               //邮箱
        String MOBILE = secureAttrs.get("MOBILE")!=null?secureAttrs.get("MOBILE").toString():"";                            //手机号
        String ENCRYPTIONTY = secureAttrs.get("ENCRYPTIONTY")!=null?secureAttrs.get("ENCRYPTIONTY").toString():"";          //密码加密类型 1md5 2 sha-1
        String  CREATOR = secureAttrs.get("CREATOR")!=null?secureAttrs.get("CREATOR").toString():"";                        //创建者ID
        String CORPORATE_PHONE = secureAttrs.get("CORPORATE_PHONE")!=null?secureAttrs.get("CORPORATE_PHONE").toString():""; //联系电话
        String REGISTER_ADDRESS = secureAttrs.get("REGISTER_ADDRESS")!=null?secureAttrs.get("REGISTER_ADDRESS").toString():"";//注册地址
        String REGISTER_AGENCY = secureAttrs.get("REGISTER_AGENCY")!=null?secureAttrs.get("REGISTER_AGENCY").toString():""; //审批机关
        String AGENT_NAME = secureAttrs.get("AGENT_NAME")!=null?secureAttrs.get("AGENT_NAME").toString():"";                //办理人姓名
        String AGENT_MOBILE = secureAttrs.get("AGENT_MOBILE")!=null?secureAttrs.get("AGENT_MOBILE").toString():"";          //办理人手机
        String AGENT_CARD_NO = secureAttrs.get("AGENT_CARD_NO")!=null?secureAttrs.get("AGENT_CARD_NO").toString():"";       //办理人身份证号
        String CORPORATE_PICTURE = secureAttrs.get("CORPORATE_PICTURE")!=null?secureAttrs.get("CORPORATE_PICTURE").toString():"";//法人证照
        String REAL_AUTH = secureAttrs.get("REAL_AUTH")!=null?secureAttrs.get("REAL_AUTH").toString():"0";                  //实名认证类型（以逗号分隔隔） 0 未认证 1 网上证照实名认证 9 实体大厅现场审核验证
        String REGISTER_TYPE = secureAttrs.get("REGISTER_TYPE")!=null?secureAttrs.get("REGISTER_TYPE").toString():"";       //注册来源 0 网上自主注册 1 实体大厅现成注册
        String ADDRESS = secureAttrs.get("ADDRESS")!=null?secureAttrs.get("ADDRESS").toString():"";                         //联系地址

        String incname = CORPORATE_NAME;
        //String inctype = "5"; //企业类型，1国有，2民营，3外资，4港澳台资，5其他
        //String incisnew = "";// 是否办理新设立企业，0否，1是
        //String incpermit = "";//营业执照
        String inczzjgdm = CORPORATE_CODE;
        String incdeputy =  REPRESENTATIVE_NAME;
        String incpid = REPRESENTATIVE_CARD_NO;
        String agename = AGENT_NAME;
        String agepid = AGENT_CARD_NO;
        String ageemail = EMAIL;
        String agephone = AGENT_MOBILE;
        //String ageindicia = ""; //邮政编码
        String ageaddr = ADDRESS;
        String realauth=REAL_AUTH;
        
        incinfo.setIncname(incname);
        //incinfo.setInctype(inctype);
        //incinfo.setIncisnew(incisnew);
        //incinfo.setIncpermit(incpermit);
        incinfo.setInczzjgdm(inczzjgdm);
        incinfo.setIncdeputy(incdeputy);
        incinfo.setIncpid(incpid);
        incinfo.setAgename(agename);
        incinfo.setAgepid(agepid);
        incinfo.setAgeemail(ageemail);
        incinfo.setAgephone(agephone);
        //incinfo.setAgeindicia(ageindicia);
        incinfo.setAgeaddr(ageaddr);
        incinfo.setRealauth(realauth);
        incinfo.setInclogindate(new Timestamp(System.currentTimeMillis()));
        return incinfo;
        
    }
    @SuppressWarnings("unused")
    protected TWebHallUserInforMation mapToBean(Map<String,Object> secureAttrs,TWebHallUserInforMation userinfo){
        String USER_TYPE =secureAttrs.get("USER_TYPE")!=null?secureAttrs.get("USER_TYPE").toString():"";
        String USER_ID=secureAttrs.get("USER_ID")!=null?secureAttrs.get("USER_ID").toString():"";
        String LOGIN_NAME=secureAttrs.get("LOGIN_NAME")!=null?secureAttrs.get("LOGIN_NAME").toString():"";
        String LOGIN_PWD=secureAttrs.get("LOGIN_PWD")!=null?secureAttrs.get("LOGIN_PWD").toString():"";
        String CREATE_TIME=secureAttrs.get("CREATE_TIME")!=null?secureAttrs.get("CREATE_TIME").toString():"";
        String USER_NAME=secureAttrs.get("USER_NAME")!=null?secureAttrs.get("USER_NAME").toString():"";
        String MOBILE=secureAttrs.get("MOBILE")!=null?secureAttrs.get("MOBILE").toString():"";
        String CARD_NO=secureAttrs.get("CARD_NO")!=null?secureAttrs.get("CARD_NO").toString():"";
        String STATUS=secureAttrs.get("STATUS")!=null?secureAttrs.get("STATUS").toString():"";
        String SORT_ORDER=secureAttrs.get("SORT_ORDER")!=null?secureAttrs.get("SORT_ORDER").toString():"";
        String ENCRYPTIONTY=secureAttrs.get("ENCRYPTIONTY")!=null?secureAttrs.get("ENCRYPTIONTY").toString():"";
        String REAL_AUTH=secureAttrs.get("REAL_AUTH")!=null?secureAttrs.get("REAL_AUTH").toString():"0";
        String SEX=secureAttrs.get("SEX")!=null?secureAttrs.get("SEX").toString():"";
        String BIRTHDAY=secureAttrs.get("BIRTHDAY")!=null?secureAttrs.get("BIRTHDAY").toString():"";
        String BANK_NO=secureAttrs.get("BANK_NO")!=null?secureAttrs.get("BANK_NO").toString():"";
        String EMAIL=secureAttrs.get("EMAIL")!=null?secureAttrs.get("EMAIL").toString():"";
        String ALIPAY_NO=secureAttrs.get("ALIPAY_NO")!=null?secureAttrs.get("ALIPAY_NO").toString():"";
        String CREATOR=secureAttrs.get("CREATOR")!=null?secureAttrs.get("CREATOR").toString():"";
        String SOCIAL_CARD=secureAttrs.get("SOCIAL_CARD")!=null?secureAttrs.get("SOCIAL_CARD").toString():"";
        String FUND_CARD=secureAttrs.get("FUND_CARD")!=null?secureAttrs.get("FUND_CARD").toString():"";
        String CAR_CARD=secureAttrs.get("CAR_CARD")!=null?secureAttrs.get("CAR_CARD").toString():"";
        String REGISTER_TYPE=secureAttrs.get("REGISTER_TYPE")!=null?secureAttrs.get("REGISTER_TYPE").toString():"";
        String ADDRESS=secureAttrs.get("ADDRESS")!=null?secureAttrs.get("ADDRESS").toString():"";

        /* 个人用户信息收集 */
        String name = USER_NAME;
        String usergender = SEX;
        String certificatetype = "1";//证件类型   默认设置身份证号码
        String userpid = CARD_NO;
        String userphone = MOBILE;
        String usermobile = MOBILE;
        String useremail = EMAIL;
        String useraddress = ADDRESS; //地址
        String userindicia = EMAIL;//邮编
        String realauth=REAL_AUTH;
        //userinfo.setUseruid(Integer.parseInt(USER_ID));
        userinfo.setUsername(name);
        userinfo.setUsergender(usergender);
        userinfo.setCertificatetype(certificatetype);
        userinfo.setUserpid(userpid);
        userinfo.setUserphone(userphone);
        userinfo.setUsermobile(usermobile);
        userinfo.setUseremail(useremail);
        userinfo.setUseraddress(useraddress);
        userinfo.setUserindicia(userindicia);
        userinfo.setUserdate(new Timestamp(System.currentTimeMillis()));
        userinfo.setRealauth(realauth);
        return userinfo;
    }

    /**
     * 取消cookie
     * @param request
     * @param response
     */
    protected void removeCookieErrorRemaining(HttpServletRequest request,
            HttpServletResponse response) {
        CookieUtils.cancleCookie(request, response, COOKIE_ERROR_REMAINING, null);
    }

}
