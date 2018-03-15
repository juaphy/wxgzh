package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的办事指南事项详细信息
 * @author swc 2018-03-15
 *
 */
public class PermissionItemInfo implements Serializable{
    private static final long serialVersionUID = 7813047841113853839L;

    private String id; // 事项id
    private String sxzxname; // 省内输气管网或年输气能力5亿立方米以下建设项目
    private String deptid; // 部门id
    private String deptname; // 省发展改革委
    private String xzxk; // 事项类型：1行政许可，2非行政许可，3行政服务，4行政处
    private String sptj; // 审批条件(办理条件)
    private String spcx; // 审批程序（窗口办理流程）
    private String limitdays; // 办理时限
    private String limitunit; // 办理时限单位
    private String limittime; // 法定时限
    private String fdsxdw; // 法定时限单位
    private String finishtime; // 完成时限（特别程序）
    private String finishlimit; // 完成时限单位
    private String spsljgmc ; // 权利部门 (如：贵州省发展和改革委员会)
    private String windowsval; // 贵州省网上办事大厅新增的窗口(窗口内容)
    private String blsjdetails ; // 办理时间
    private String bsdx ; // 办理对象
    private String ischange ; // 是否收费(事项是否收费：0-不收费，1-收费)
    private String charge; // 收费标准及依据
    private String sfyj ; // 收费依据（停用）
    private String zxytxfx; //  咨询信息（咨询电话等等）
    private String txfx; // 投诉信息
    private String xzssjxzfyfx; // 行政复议行政诉讼信息
    private String speprmflag; // 特别程序(特殊环节)（1:有特别程序 0:无）
    private String speprmname; // 特别程序名称
    private String speprmflagsfsf; // 特别程序是否收费（0：不收费，1：收费）
    private String tshjyj; // 特殊环节法律依据及描述
    private String byzda; // 特殊环节收费依据及描述
    private String cjwtjd; // 常见问题解答
    private String qlly; // 权利来源
    private String bzlx; // 办件类型
    private String xksl; // 许可数量
    private String wssbaddress; // 网上申请
    private String sfydsb; // 1:网上申报，0：非网上申报
    private String publicsite; // 审批公示
    private String blcx; // 办件查询
    private String nshnj; // 年审年检
    // 申请材料列表
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public String getXzxk() {
        return xzxk;
    }
    public void setXzxk(String xzxk) {
        this.xzxk = xzxk;
    }
    public String getSptj() {
        return sptj;
    }
    public void setSptj(String sptj) {
        this.sptj = sptj;
    }
    public String getSpcx() {
        return spcx;
    }
    public void setSpcx(String spcx) {
        this.spcx = spcx;
    }
    public String getLimitdays() {
        return limitdays;
    }
    public void setLimitdays(String limitdays) {
        this.limitdays = limitdays;
    }
    public String getLimitunit() {
        return limitunit;
    }
    public void setLimitunit(String limitunit) {
        this.limitunit = limitunit;
    }
    public String getLimittime() {
        return limittime;
    }
    public void setLimittime(String limittime) {
        this.limittime = limittime;
    }
    public String getFdsxdw() {
        return fdsxdw;
    }
    public void setFdsxdw(String fdsxdw) {
        this.fdsxdw = fdsxdw;
    }
    public String getFinishtime() {
        return finishtime;
    }
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }
    public String getFinishlimit() {
        return finishlimit;
    }
    public void setFinishlimit(String finishlimit) {
        this.finishlimit = finishlimit;
    }
    public String getSpsljgmc() {
        return spsljgmc;
    }
    public void setSpsljgmc(String spsljgmc) {
        this.spsljgmc = spsljgmc;
    }
    public String getWindowsval() {
        return windowsval;
    }
    public void setWindowsval(String windowsval) {
        this.windowsval = windowsval;
    }
    public String getBlsjdetails() {
        return blsjdetails;
    }
    public void setBlsjdetails(String blsjdetails) {
        this.blsjdetails = blsjdetails;
    }
    public String getBsdx() {
        return bsdx;
    }
    public void setBsdx(String bsdx) {
        this.bsdx = bsdx;
    }
    public String getIschange() {
        return ischange;
    }
    public void setIschange(String ischange) {
        this.ischange = ischange;
    }
    public String getCharge() {
        return charge;
    }
    public void setCharge(String charge) {
        this.charge = charge;
    }
    public String getSfyj() {
        return sfyj;
    }
    public void setSfyj(String sfyj) {
        this.sfyj = sfyj;
    }
    public String getZxytxfx() {
        return zxytxfx;
    }
    public void setZxytxfx(String zxytxfx) {
        this.zxytxfx = zxytxfx;
    }
    public String getTxfx() {
        return txfx;
    }
    public void setTxfx(String txfx) {
        this.txfx = txfx;
    }
    public String getXzssjxzfyfx() {
        return xzssjxzfyfx;
    }
    public void setXzssjxzfyfx(String xzssjxzfyfx) {
        this.xzssjxzfyfx = xzssjxzfyfx;
    }
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
    public String getCjwtjd() {
        return cjwtjd;
    }
    public void setCjwtjd(String cjwtjd) {
        this.cjwtjd = cjwtjd;
    }
    public String getQlly() {
        return qlly;
    }
    public void setQlly(String qlly) {
        this.qlly = qlly;
    }
    public String getBzlx() {
        return bzlx;
    }
    public void setBzlx(String bzlx) {
        this.bzlx = bzlx;
    }
    public String getXksl() {
        return xksl;
    }
    public void setXksl(String xksl) {
        this.xksl = xksl;
    }
    public String getWssbaddress() {
        return wssbaddress;
    }
    public void setWssbaddress(String wssbaddress) {
        this.wssbaddress = wssbaddress;
    }
    public String getSfydsb() {
        return sfydsb;
    }
    public void setSfydsb(String sfydsb) {
        this.sfydsb = sfydsb;
    }
    public String getPublicsite() {
        return publicsite;
    }
    public void setPublicsite(String publicsite) {
        this.publicsite = publicsite;
    }
    public String getBlcx() {
        return blcx;
    }
    public void setBlcx(String blcx) {
        this.blcx = blcx;
    }
    public String getNshnj() {
        return nshnj;
    }
    public void setNshnj(String nshnj) {
        this.nshnj = nshnj;
    }

}
