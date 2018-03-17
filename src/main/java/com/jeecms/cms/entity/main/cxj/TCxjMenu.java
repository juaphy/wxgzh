package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询机-菜单
 * @author swc 2018-03-17
 *
 */
public class TCxjMenu implements Serializable {
    private static final long serialVersionUID = -6870051062541735965L;

    private String id; // id
    private String areaid; // 区划id
    private String name; // 菜单名称
    private String tag; // 菜单标识符
    private String url; // 菜单访问路径
    private String icon; // 图标路径
    private String classname; // class名
    private int ordernum; // 排序号
    private String status; // 状态：0-无效、1-有效
    private String imgsrc; // img标签路径
    private String homeUrl; // 首页url
    private String remark; // 备注
    private Date createtime; // 创建时间
    private String createUsername; // 创建人名称
    private String createUserid; // 创建人id
    private Date updatetime; // 更新时间
    private String updateUsername; // 更新人名称
    private String updateUserid; // 更新人id

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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getClassname() {
        return classname;
    }
    public void setClassname(String classname) {
        this.classname = classname;
    }
    public int getOrdernum() {
        return ordernum;
    }
    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getImgsrc() {
        return imgsrc;
    }
    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
    public String getHomeUrl() {
        return homeUrl;
    }
    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getCreateUsername() {
        return createUsername;
    }
    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }
    public String getCreateUserid() {
        return createUserid;
    }
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public String getUpdateUsername() {
        return updateUsername;
    }
    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }
    public String getUpdateUserid() {
        return updateUserid;
    }
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

}