package com.jeecms.cms.entity.main.wssb;

import org.apache.commons.lang.StringUtils;

import com.jeecms.core.entity.CmsUser;

/**
 * 网厅用户-法人信息
 * @author swc 2018-04-16
 *
 */
public class TWebHallIncInforMation {
    private java.lang.Integer incid;            // 主键用户id
    private java.lang.String incname;           // 企业名称
    private java.lang.String inctype;           // 企业类型，1国有，2民营，3外资，4港澳台资，5其他
    private java.lang.String incisnew;          // 是否办理新设立企业，0否，1是
    private java.lang.String incpermit;         // 营业执照
    private java.lang.String inczzjgdm;         // 组织机构代码
    private java.lang.String incdeputy;         // 法人代表
    private java.lang.String incpid;            // 法人身份证号码
    private java.lang.String incaddr;           // 企业地址
    private java.lang.String incindicia;        // 企业邮编
    private java.lang.String incphone;          // 企业电话
    private java.lang.String incfax;            // 企业传真
    private java.util.Date inclogindate;        // 注册日期
    private java.lang.String incnetwork;        // 企业网址
    private java.lang.String incemail;          // 企业邮箱
    private java.lang.String incca;             // CA认证
    private java.lang.String agename;           // 企业联系人姓名
    private java.lang.String agepid;            // 企业联系人身份证
    private java.lang.String ageemail;          // 企业联系人电子邮件
    private java.lang.String agemobile;         // 企业联系人移动电话
    private java.lang.String agephone;          // 企业联系人固定电话
    private java.lang.String ageindicia;        // 企业联系人邮政编码
    private java.lang.String ageaddr;           // 企业联系人联系地址
    private java.lang.String isrealname;        //是否实名制，0否，1是
    private java.lang.String imgfront;          //照片id 正面
    private java.lang.String imgback;           //照片id 反面
    private java.lang.String realauth;          // 实名认证类型
    private CmsUser user; 

    public void blankToNull() {
        // 将空串设置为null，便于前台处理。
        if (StringUtils.isBlank(getIncname())) {
            setIncname(null);
        }
        if (StringUtils.isBlank(getInctype())) {
            setInctype(null);
        }
        if (StringUtils.isBlank(getIncisnew())) {
            setIncisnew(null);
        }
        if (StringUtils.isBlank(getIncpermit())) {
            setIncpermit(null);
        }
        if (StringUtils.isBlank(getInczzjgdm())) {
            setInczzjgdm(null);
        }
        if (StringUtils.isBlank(getIncdeputy())) {
            setIncdeputy(null);
        }
        if (StringUtils.isBlank(getIncpid())) {
            setIncpid(null);
        }
        if (StringUtils.isBlank(getIncaddr())) {
            setIncaddr(null);
        }
        if (StringUtils.isBlank(getIncindicia())) {
            setIncindicia(null);
        }
        if (StringUtils.isBlank(getIncphone())) {
            setIncphone(null);
        }
        if (StringUtils.isBlank(getIncfax())) {
            setIncfax(null);
        }
        if (StringUtils.isBlank(getIncnetwork())) {
            setIncnetwork(null);
        }
        if (StringUtils.isBlank(getIncemail())) {
            setIncemail(null);
        }
        if (StringUtils.isBlank(getIncca())) {
            setIncca(null);
        }
        if (StringUtils.isBlank(getAgename())) {
            setAgename(null);
        }
        if (StringUtils.isBlank(getAgepid())) {
            setAgepid(null);
        }
        if (StringUtils.isBlank(getAgeemail())) {
            setAgeemail(null);
        }
        if (StringUtils.isBlank(getAgemobile())) {
            setAgemobile(null);
        }
        if (StringUtils.isBlank(getAgephone())) {
            setAgephone(null);
        }
        if (StringUtils.isBlank(getAgeindicia())) {
            setAgeindicia(null);
        }
        if (StringUtils.isBlank(getAgeaddr())) {
            setAgeaddr(null);
        }
        if (StringUtils.isBlank(getIsrealname())) {
            setIsrealname(null);
        }
        if (StringUtils.isBlank(getImgfront())) {
            setImgfront(null);
        }
        if (StringUtils.isBlank(getImgback())) {
            setImgback(null);
        }
    }
    

    public java.lang.Integer getIncid() {
        return incid;
    }


    public void setIncid(java.lang.Integer incid) {
        this.incid = incid;
    }


    public java.lang.String getIncname() {
        return incname;
    }


    public void setIncname(java.lang.String incname) {
        this.incname = incname;
    }


    public java.lang.String getInctype() {
        return inctype;
    }


    public void setInctype(java.lang.String inctype) {
        this.inctype = inctype;
    }


    public java.lang.String getIncisnew() {
        return incisnew;
    }


    public void setIncisnew(java.lang.String incisnew) {
        this.incisnew = incisnew;
    }


    public java.lang.String getIncpermit() {
        return incpermit;
    }


    public void setIncpermit(java.lang.String incpermit) {
        this.incpermit = incpermit;
    }


    public java.lang.String getInczzjgdm() {
        return inczzjgdm;
    }


    public void setInczzjgdm(java.lang.String inczzjgdm) {
        this.inczzjgdm = inczzjgdm;
    }


    public java.lang.String getIncdeputy() {
        return incdeputy;
    }


    public void setIncdeputy(java.lang.String incdeputy) {
        this.incdeputy = incdeputy;
    }


    public java.lang.String getIncpid() {
        return incpid;
    }


    public void setIncpid(java.lang.String incpid) {
        this.incpid = incpid;
    }


    public java.lang.String getIncaddr() {
        return incaddr;
    }


    public void setIncaddr(java.lang.String incaddr) {
        this.incaddr = incaddr;
    }


    public java.lang.String getIncindicia() {
        return incindicia;
    }


    public void setIncindicia(java.lang.String incindicia) {
        this.incindicia = incindicia;
    }


    public java.lang.String getIncphone() {
        return incphone;
    }


    public void setIncphone(String incphone) {
        this.incphone = incphone;
    }


    public java.lang.String getIncfax() {
        return incfax;
    }


    public void setIncfax(java.lang.String incfax) {
        this.incfax = incfax;
    }


    public java.util.Date getInclogindate() {
        return inclogindate;
    }


    public void setInclogindate(java.util.Date inclogindate) {
        this.inclogindate = inclogindate;
    }


    public java.lang.String getIncnetwork() {
        return incnetwork;
    }


    public void setIncnetwork(java.lang.String incnetwork) {
        this.incnetwork = incnetwork;
    }


    public java.lang.String getIncemail() {
        return incemail;
    }


    public void setIncemail(java.lang.String incemail) {
        this.incemail = incemail;
    }


    public java.lang.String getIncca() {
        return incca;
    }


    public void setIncca(java.lang.String incca) {
        this.incca = incca;
    }


    public java.lang.String getAgename() {
        return agename;
    }


    public void setAgename(java.lang.String agename) {
        this.agename = agename;
    }


    public java.lang.String getAgepid() {
        return agepid;
    }


    public void setAgepid(java.lang.String agepid) {
        this.agepid = agepid;
    }


    public java.lang.String getAgeemail() {
        return ageemail;
    }


    public void setAgeemail(java.lang.String ageemail) {
        this.ageemail = ageemail;
    }


    public java.lang.String getAgemobile() {
        return agemobile;
    }


    public void setAgemobile(java.lang.String agemobile) {
        this.agemobile = agemobile;
    }


    public java.lang.String getAgephone() {
        return agephone;
    }


    public void setAgephone(java.lang.String agephone) {
        this.agephone = agephone;
    }


    public java.lang.String getAgeindicia() {
        return ageindicia;
    }


    public void setAgeindicia(java.lang.String ageindicia) {
        this.ageindicia = ageindicia;
    }


    public java.lang.String getAgeaddr() {
        return ageaddr;
    }


    public void setAgeaddr(java.lang.String ageaddr) {
        this.ageaddr = ageaddr;
    }
    
    public java.lang.String getIsrealname() {
        return isrealname;
    }

    public void setIsrealname(java.lang.String isrealname) {
        this.isrealname = isrealname;
    }
    
    public java.lang.String getImgfront() {
        return imgfront;
    }

    public void setImgfront(java.lang.String imgfront) {
        this.imgfront = imgfront;
    }

    public java.lang.String getImgback() {
        return imgback;
    }

    public void setImgback(java.lang.String imgback) {
        this.imgback = imgback;
    }

    public CmsUser getUser() {
        return user;
    }


    public void setUser(CmsUser user) {
        this.user = user;
    }


	public java.lang.String getRealauth() {
		return realauth;
	}


	public void setRealauth(java.lang.String realauth) {
		this.realauth = realauth;
	}
    
}
