package com.jeecms.cms.entity.main.wssb;

import org.apache.commons.lang.StringUtils;

import com.jeecms.core.entity.CmsUser;

/**
 * 网厅用户信息Entity
 * @author swc 2018-04-16
 *
 */
public class TWebHallUserInforMation {

    private java.lang.Integer useruid;          // 主键用户id
    private java.lang.String username;          // 名称
    private java.lang.String usergender;        // 性别 0女，1男，空表示保密
    private java.lang.String certificatetype;   // 证件类型，1身份证，2护照，3军官证，4退伍证，5驾驶证，6学生证
    private java.lang.String userpid;           // 证件号码
    private java.lang.String userphone;         // 电话
    private java.lang.String usermobile;        // 手机
    private java.lang.String useremail;         // 邮箱
    private java.lang.String useraddress;       // 地址
    private java.lang.String userindicia;       // 邮编
    private java.util.Date userdate;            // 注册时间
    private java.lang.String userca;            // CA认证
    private java.lang.String isrealname;        // 是否实名制，0否，1是
    private java.lang.String imgfront;          // 照片id 正面
    private java.lang.String imgback;           // 照片id 反面
    private java.lang.String realauth;          // 实名认证类型
    private CmsUser user;
 
    public void blankToNull() {
        // 将空串设置为null，便于前台处理。
        if (StringUtils.isBlank(getUsername())) {
            setUsername(null);
        }
        if (StringUtils.isBlank(getUsergender())) {
            setUsergender(null);
        }
        if (StringUtils.isBlank(getCertificatetype())) {
            setCertificatetype(null);
        }
        if (StringUtils.isBlank(getUserpid())) {
            setUserpid(null);
        }
        if (StringUtils.isBlank(getUserphone())) {
            setUserphone(null);
        }
        if (StringUtils.isBlank(getUsermobile())) {
            setUsermobile(null);
        }
        if (StringUtils.isBlank(getUseremail())) {
            setUseremail(null);
        }
        if (StringUtils.isBlank(getUseraddress())) {
            setUseraddress(null);
        }
        if (StringUtils.isBlank(getUserindicia())) {
            setUserindicia(null);
        }
        if (StringUtils.isBlank(getUserca())) {
            setUserca(null);
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

    public java.lang.String getUsername() {
        return username;
    }

    public void setUsername(java.lang.String username) {
        this.username = username;
    }

    public java.lang.String getUsergender() {
        return usergender;
    }

    public void setUsergender(java.lang.String usergender) {
        this.usergender = usergender;
    }

    public java.lang.String getCertificatetype() {
        return certificatetype;
    }

    public void setCertificatetype(java.lang.String certificatetype) {
        this.certificatetype = certificatetype;
    }

    public java.lang.String getUserpid() {
        return userpid;
    }

    public void setUserpid(java.lang.String userpid) {
        this.userpid = userpid;
    }

    public java.lang.String getUserphone() {
        return userphone;
    }

    public void setUserphone(java.lang.String userphone) {
        this.userphone = userphone;
    }

    public java.lang.String getUsermobile() {
        return usermobile;
    }

    public void setUsermobile(java.lang.String usermobile) {
        this.usermobile = usermobile;
    }

    public java.lang.String getUseremail() {
        return useremail;
    }

    public void setUseremail(java.lang.String useremail) {
        this.useremail = useremail;
    }

    public java.lang.String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(java.lang.String useraddress) {
        this.useraddress = useraddress;
    }

    public java.lang.String getUserindicia() {
        return userindicia;
    }

    public void setUserindicia(java.lang.String userindicia) {
        this.userindicia = userindicia;
    }

    public java.util.Date getUserdate() {
        return userdate;
    }

    public void setUserdate(java.util.Date userdate) {
        this.userdate = userdate;
    }

    public java.lang.String getUserca() {
        return userca;
    }

    public void setUserca(java.lang.String userca) {
        this.userca = userca;
    }

    public java.lang.Integer getUseruid() {
        return useruid;
    }

    public void setUseruid(java.lang.Integer useruid) {
        this.useruid = useruid;
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
