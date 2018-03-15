package com.jeecms.cms.rest2.entity;

import java.io.Serializable;

/**
 * 调用接口返回的事项信息
 * @author swc 2018-03-15
 *
 */
public class SxInfo implements Serializable{
    private static final long serialVersionUID = -620139193928664541L;

    private String id; // 事项ID
    private String largeitemid; // 大项编号
    private String smallitemid; // 小项编号
    private String sxzxname; // 事项小项名称
    private String itemversion; // 事项版本号
    private String itemlimittime; // 审批事项时限
    private String itemlimitunit; // 审批事项时限单位，G工作日，Z自然日
    private String regionid; // 行政区划代码
    private String deptid; // 部门组织机构代码
    private String deptname; // 部门名称
    private String lawaddr; // 法律规定管辖地（默认行政区划代码）
    private String realaddr; // 发生业务管辖地（默认行政区划代码）
    private String xzxk; // 事项类型
    private String sfydsb; // 是否能在移动端申报，0否，1是
    private String isreserve; // 是否提供网上预约服务，0否，1是

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLargeitemid() {
        return largeitemid;
    }
    public void setLargeitemid(String largeitemid) {
        this.largeitemid = largeitemid;
    }
    public String getSmallitemid() {
        return smallitemid;
    }
    public void setSmallitemid(String smallitemid) {
        this.smallitemid = smallitemid;
    }
    public String getSxzxname() {
        return sxzxname;
    }
    public void setSxzxname(String sxzxname) {
        this.sxzxname = sxzxname;
    }
    public String getItemversion() {
        return itemversion;
    }
    public void setItemversion(String itemversion) {
        this.itemversion = itemversion;
    }
    public String getItemlimittime() {
        return itemlimittime;
    }
    public void setItemlimittime(String itemlimittime) {
        this.itemlimittime = itemlimittime;
    }
    public String getItemlimitunit() {
        return itemlimitunit;
    }
    public void setItemlimitunit(String itemlimitunit) {
        this.itemlimitunit = itemlimitunit;
    }
    public String getRegionid() {
        return regionid;
    }
    public void setRegionid(String regionid) {
        this.regionid = regionid;
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
    public String getLawaddr() {
        return lawaddr;
    }
    public void setLawaddr(String lawaddr) {
        this.lawaddr = lawaddr;
    }
    public String getRealaddr() {
        return realaddr;
    }
    public void setRealaddr(String realaddr) {
        this.realaddr = realaddr;
    }
    public String getXzxk() {
        return xzxk;
    }
    public void setXzxk(String xzxk) {
        this.xzxk = xzxk;
    }
    public String getSfydsb() {
        return sfydsb;
    }
    public void setSfydsb(String sfydsb) {
        this.sfydsb = sfydsb;
    }
    public String getIsreserve() {
        return isreserve;
    }
    public void setIsreserve(String isreserve) {
        this.isreserve = isreserve;
    }

}
