package com.jeecms.cms.rest2.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 网厅接口数据-业务基本信息
 * @author swc 2018-03-20
 *
 */
public class BusinessInfo implements Serializable {
    private static final long serialVersionUID = 8903689361871129821L;
    private String bsnum; // 业务编号（受理编号）
    private String sxzxname; // 事项名称（受理事项）
    private String deptid; // 部门id
    private String deptname; // 部门名称（受理部门）
    private String appname; // 申请人
    private String appcompany; // 申请单位
    private String applytime; // 申请时间（申报时间）
    private String status; // 状态
    private String cstatus; // 状态名称
    private List<Logs> logs; // 办理信息
    public String getBsnum() {
        return bsnum;
    }
    public void setBsnum(String bsnum) {
        this.bsnum = bsnum;
    }
    public String getSxzxname() {
        return sxzxname;
    }
    public void setSxzxname(String sxzxname) {
        this.sxzxname = sxzxname;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getDeptname() {
        return deptname;
    }
    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }
    public String getAppname() {
        return appname;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
    public String getAppcompany() {
        return appcompany;
    }
    public void setAppcompany(String appcompany) {
        this.appcompany = appcompany;
    }
    public String getApplytime() {
        return applytime;
    }
    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCstatus() {
        return cstatus;
    }
    public List<Logs> getLogs() {
        return logs;
    }
    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

}