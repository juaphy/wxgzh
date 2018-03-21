package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询机-系统用户表
 * @author swc 2018-03-20
 *
 */
public class TSysUser implements Serializable {
    private static final long serialVersionUID = -4396512284565517002L;
    private String id; // 编号
    private String account; // 用户账号
    private String username; // 用户名
    private String password; // 密码
    private String deptid; // 所属部门编号
    private String cardid; // 身份证号码
    private String sex; // 性别，M男，W女
    private String address; // 地址
    private String email; // 邮件
    private String phone; // 座机电话
    private String mobile; // 手机号码
    private String staffid; // 员工编号
    private Date createtime; // 创建时间
    private Date modtime; // 修改时间
    private String status; // 状态，0无效，1有效
    private String remark; // 备注
    private String type; // 用户类型1：超级管理员，2：部门管理员，3：普通用户
    private String caid; // CA证书编号
    private String reserveone; // 头像上传路径
    private String reservetwo; // 备用字段2
    private Date reservethree; // 备用字段3
    private Date birthday; // 出生日期
    private Date partyday; // 入党日期
    private Date carrerday; // 上岗日期
    private String degree; // 学历
    private String imageurl; // 用户图片
    private String menustyle; // 菜单风格
    private String systemstyle; // 系统风格
    private String exchange; // 交换状态，0否，1是
    private String windowid; // 窗口id
    private String windowname; // 窗口名称
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getDeptid() {
        return deptid;
    }
    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }
    public String getCardid() {
        return cardid;
    }
    public void setCardid(String cardid) {
        this.cardid = cardid;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getStaffid() {
        return staffid;
    }
    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Date getModtime() {
        return modtime;
    }
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCaid() {
        return caid;
    }
    public void setCaid(String caid) {
        this.caid = caid;
    }
    public String getReserveone() {
        return reserveone;
    }
    public void setReserveone(String reserveone) {
        this.reserveone = reserveone;
    }
    public String getReservetwo() {
        return reservetwo;
    }
    public void setReservetwo(String reservetwo) {
        this.reservetwo = reservetwo;
    }
    public Date getReservethree() {
        return reservethree;
    }
    public void setReservethree(Date reservethree) {
        this.reservethree = reservethree;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Date getPartyday() {
        return partyday;
    }
    public void setPartyday(Date partyday) {
        this.partyday = partyday;
    }
    public Date getCarrerday() {
        return carrerday;
    }
    public void setCarrerday(Date carrerday) {
        this.carrerday = carrerday;
    }
    public String getDegree() {
        return degree;
    }
    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getMenustyle() {
        return menustyle;
    }
    public void setMenustyle(String menustyle) {
        this.menustyle = menustyle;
    }
    public String getSystemstyle() {
        return systemstyle;
    }
    public void setSystemstyle(String systemstyle) {
        this.systemstyle = systemstyle;
    }
    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }
    public String getWindowid() {
        return windowid;
    }
    public void setWindowid(String windowid) {
        this.windowid = windowid;
    }
    public String getWindowname() {
        return windowname;
    }
    public void setWindowname(String windowname) {
        this.windowname = windowname;
    }

}