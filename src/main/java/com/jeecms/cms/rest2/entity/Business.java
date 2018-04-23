package com.jeecms.cms.rest2.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
   
/**
 * Business xml 对应bean
 * 
 * @author 罗勇
 * 
 * @date 2014-4-21
 */
@XmlRootElement
public class Business {
    private String businessid;// 原系统业务流水号
    private String cbusinessid;// 协同业务流水号
    private String citemid;// 协同事项编号
    private String citemversion;// 协同事项版本号
    private String permid;// 审批事项ID
    private String largeitemid;// 审批事项大项编号
    private String smallitemid;// 审批事项小项编号
    private String smallitemname;// 审批事项小项名称
    private String itemversion;// 审批事项版本号
    private String itemlimittime;// 审批事项时限
    private String itemlimitunit;// 审批事项时限单位
    private String regionid;// 行政区划代码
    private String deptcode;// 部门组织机构代码
    private String deptname;// 部门名称
    private String projectname;// 申请项目名称
    private String receiptid;// 业务回执号
    private String submittype;// 提交方式
    private String applytime;// 登记时间（窗口受理时间或网上申请时间）
    private String endtime;// 办结时间
    private String lawaddr;// 法律规定管辖地
    private String realaddr;// 发生业务管辖地
    private String status;// 状态
    private String applicantid;// 用户ID
    private String state;
    private String groupid;
    private String groupname;

    @XmlElement
    public String getBusinessid() {
        return businessid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    @XmlElement
    public String getCbusinessid() {
        return cbusinessid;
    }

    public void setCbusinessid(String cbusinessid) {
        this.cbusinessid = cbusinessid;
    }

    @XmlElement
    public String getCitemid() {
        return citemid;
    }

    public void setCitemid(String citemid) {
        this.citemid = citemid;
    }

    @XmlElement
    public String getCitemversion() {
        return citemversion;
    }

    public void setCitemversion(String citemversion) {
        this.citemversion = citemversion;
    }

    @XmlElement
    public String getPermid() {
        return permid;
    }

    public void setPermid(String permid) {
        this.permid = permid;
    }

    @XmlElement
    public String getLargeitemid() {
        return largeitemid;
    }

    public void setLargeitemid(String largeitemid) {
        this.largeitemid = largeitemid;
    }

    @XmlElement
    public String getSmallitemid() {
        return smallitemid;
    }

    public void setSmallitemid(String smallitemid) {
        this.smallitemid = smallitemid;
    }

    @XmlElement
    public String getSmallitemname() {
        return smallitemname;
    }

    public void setSmallitemname(String smallitemname) {
        this.smallitemname = smallitemname;
    }

    @XmlElement
    public String getItemversion() {
        return itemversion;
    }

    public void setItemversion(String itemversion) {
        this.itemversion = itemversion;
    }

    @XmlElement
    public String getItemlimittime() {
        return itemlimittime;
    }

    public void setItemlimittime(String itemlimittime) {
        this.itemlimittime = itemlimittime;
    }

    @XmlElement
    public String getItemlimitunit() {
        return itemlimitunit;
    }

    public void setItemlimitunit(String itemlimitunit) {
        this.itemlimitunit = itemlimitunit;
    }

    @XmlElement
    public String getRegionid() {
        return regionid;
    }

    public void setRegionid(String regionid) {
        this.regionid = regionid;
    }

    @XmlElement
    public String getDeptcode() {
        return deptcode;
    }

    public void setDeptcode(String deptcode) {
        this.deptcode = deptcode;
    }

    @XmlElement
    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    @XmlElement
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @XmlElement
    public String getReceiptid() {
        return receiptid;
    }

    public void setReceiptid(String receiptid) {
        this.receiptid = receiptid;
    }

    @XmlElement
    public String getSubmittype() {
        return submittype;
    }

    public void setSubmittype(String submittype) {
        this.submittype = submittype;
    }

    @XmlElement
    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    @XmlElement
    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @XmlElement
    public String getLawaddr() {
        return lawaddr;
    }

    public void setLawaddr(String lawaddr) {
        this.lawaddr = lawaddr;
    }

    @XmlElement
    public String getRealaddr() {
        return realaddr;
    }

    public void setRealaddr(String realaddr) {
        this.realaddr = realaddr;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getApplicantid() {
        return applicantid;
    }

    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }

    @XmlElement
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlElement
	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@XmlElement
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}
