package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的事项材料信息
 * @author swc 2018-04-17
 *
 */
public class SxClxx implements Serializable {
    private static final long serialVersionUID = 1943389344929588677L;
    private String id; // 材料定义ID
    private String clbh; // 材料编号
    private String clmc; // 材料名称
    private String dzhyq; // 电子化要求
    private String orinum; // 原件份数
    private String copynum; // 复印件份数
    private String sfby; // 是否必要，0否，1是
    private String status; // 是否提交，0否，1是
    private String formid; // 表单ID，当DZHYQ=1，有值
    private String formver; // 表单版本号，当DZHYQ=1，有值
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getClbh() {
        return clbh;
    }
    public void setClbh(String clbh) {
        this.clbh = clbh;
    }
    public String getClmc() {
        return clmc;
    }
    public void setClmc(String clmc) {
        this.clmc = clmc;
    }
    public String getDzhyq() {
        return dzhyq;
    }
    public void setDzhyq(String dzhyq) {
        this.dzhyq = dzhyq;
    }
    public String getOrinum() {
        return orinum;
    }
    public void setOrinum(String orinum) {
        this.orinum = orinum;
    }
    public String getCopynum() {
        return copynum;
    }
    public void setCopynum(String copynum) {
        this.copynum = copynum;
    }
    public String getSfby() {
        return sfby;
    }
    public void setSfby(String sfby) {
        this.sfby = sfby;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFormid() {
        return formid;
    }
    public void setFormid(String formid) {
        this.formid = formid;
    }
    public String getFormver() {
        return formver;
    }
    public void setFormver(String formver) {
        this.formver = formver;
    }

}