package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 通过接口提交网上申报信息成功后返回的业务信息
 * @author swc 2018-04-23
 *
 */
public class WssbBusinessinfo implements Serializable {
    private static final long serialVersionUID = -4009293125106107493L;
    private String bsnum; // 业务流水号
    private String pname; // 事项名称
    private String deptname; // 部门名称
    private String appname; // 申请人
    private String createtime; // 申请时间
    public String getBsnum() {
        return bsnum;
    }
    public void setBsnum(String bsnum) {
        this.bsnum = bsnum;
    }
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
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
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

}