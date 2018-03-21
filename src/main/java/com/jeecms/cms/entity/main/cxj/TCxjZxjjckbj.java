package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询机中心简介/窗口布局信息表
 * @author swc 2018-03-20
 *
 */
public class TCxjZxjjckbj implements Serializable {
    private static final long serialVersionUID = 1400902466389970312L;
    private String id; // 主键
    private String areaid; // 区划id
    private String type; // 类型（1-中心简介、2-窗口布局）
    private String title; // 标题
    private String content; // 内容
    private String status; // 状态（0-无效、1-有效）
    private int orderid; // 排序编号
    private Date createTime; // 创建时间
    private String createUserid; // 创建人
    private Date updateTime; // 更新时间
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getOrderid() {
        return orderid;
    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getCreateUserid() {
        return createUserid;
    }
    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public String getUpdateUserid() {
        return updateUserid;
    }
    public void setUpdateUserid(String updateUserid) {
        this.updateUserid = updateUserid;
    }

}