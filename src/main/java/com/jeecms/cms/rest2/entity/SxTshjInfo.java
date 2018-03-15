package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的办事指南事-特殊环节信息
 * @author swc 2018-03-15
 *
 */
public class SxTshjInfo implements Serializable{

    private static final long serialVersionUID = 8807459988701210938L;

    private String speprmflag; // 1/0(1-有特殊环节0-没有特殊环节)
    private String speprmname; // 特殊环节名称
    private String speprmflagsfsf; // 特别程序是否收费（1: 收费，0:不收费）
    private String tshjyj; // 特殊环节法律依据及描述
    private String byzda; // 特殊环节收费依据FINISHTIME:完成时限

    public String getSpeprmflag() {
        return speprmflag;
    }
    public void setSpeprmflag(String speprmflag) {
        this.speprmflag = speprmflag;
    }
    public String getSpeprmname() {
        return speprmname;
    }
    public void setSpeprmname(String speprmname) {
        this.speprmname = speprmname;
    }
    public String getSpeprmflagsfsf() {
        return speprmflagsfsf;
    }
    public void setSpeprmflagsfsf(String speprmflagsfsf) {
        this.speprmflagsfsf = speprmflagsfsf;
    }
    public String getTshjyj() {
        return tshjyj;
    }
    public void setTshjyj(String tshjyj) {
        this.tshjyj = tshjyj;
    }
    public String getByzda() {
        return byzda;
    }
    public void setByzda(String byzda) {
        this.byzda = byzda;
    }

}
