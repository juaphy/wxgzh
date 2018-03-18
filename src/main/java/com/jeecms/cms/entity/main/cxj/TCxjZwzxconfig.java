package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询机-政务中心配置信息
 * @author swc 2018-03-17
 *
 */
public class TCxjZwzxconfig implements Serializable {
    private static final long serialVersionUID = -363908853352880333L;
    private String id; // id
    private String areaid; // 区划id
    private String areaname; // 区划名称
    private String areashortname; // 区划名称简写
    private String zwzxname; // 政务中心名称
    private String zwzxshortname; // 政务中心简写
    private String homelogo; // 首页logo图片路径
    private String minlogo; // 内容页统一小logo图片路径
    private String status; // 状态：0-无效、1-有效
    private Date createtime; // 创建时间
    private String createUserid; // 创建人id
    private String createUsername; // 创建人名称
    private Date updatetime; // 更新时间
    private String updateUserid; // 更新人id
    private String updateUsername; // 更新人名称
    private String backgroundImg; // 背景图路径
    private String loginBjImg; // 管理平台登录页面背景图路径

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAreaid() {
        return areaid;
    }
    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public String getAreaname() {
        return areaname;
    }
    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
    public String getAreashortname() {
        return areashortname;
    }
    public void setAreashortname(String areashortname) {
        this.areashortname = areashortname;
    }
    public String getZwzxname() {
        return zwzxname;
    }
    public void setZwzxname(String zwzxname) {
        this.zwzxname = zwzxname;
    }
    public String getZwzxshortname() {
        return zwzxshortname;
    }
    public void setZwzxshortname(String zwzxshortname) {
        this.zwzxshortname = zwzxshortname;
    }
    public String getHomelogo() {
        return homelogo;
    }
    public void setHomelogo(String homelogo) {
        this.homelogo = homelogo;
    }
    public String getMinlogo() {
        return minlogo;
    }
    public void setMinlogo(String minlogo) {
        this.minlogo = minlogo;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getCreateUserid() {
        return createUserid;
    }
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    public String getCreateUsername() {
        return createUsername;
    }
    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public String getUpdateUserid() {
        return updateUserid;
    }
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }
    public String getUpdateUsername() {
        return updateUsername;
    }
    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }
    public String getBackgroundImg() {
        return backgroundImg;
    }
    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }
    public String getLoginBjImg() {
        return loginBjImg;
    }
    public void setLoginBjImg(String loginBjImg) {
        this.loginBjImg = loginBjImg;
    }

}