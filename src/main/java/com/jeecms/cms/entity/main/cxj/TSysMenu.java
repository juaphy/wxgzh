package com.jeecms.cms.entity.main.cxj;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询机管理平台-系统菜单
 * @author swc 2018-03-20
 *
 */
public class TSysMenu implements Serializable {
    private static final long serialVersionUID = -4441760467300946060L;

    private String id; // 主键
    private String name; // 菜单名称
    private String parentid; // 上级菜单编号
    private String url; // 访问路径
    private String parameter; // 操作参数
    private String depth; // 层次
    private int orderid; // 排序编号
    private String modperson; // 修改人
    private Date modtime; // 修改时间
    private String remark; // 备注
    private String status; // 状态1可用 0不可用
    private int leaf; // 0为叶子节点，1为父节点
    private String systemid; // 系统id
    private String type; // 菜单类型
    private String reservetwo; // 备用字段2
    private Date reservethree; // 备用字段3
    private String exchange; // 交换状态，0否，1是
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentid() {
        return parentid;
    }
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getParameter() {
        return parameter;
    }
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    public String getDepth() {
        return depth;
    }
    public void setDepth(String depth) {
        this.depth = depth;
    }
    public int getOrderid() {
        return orderid;
    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }
    public String getModperson() {
        return modperson;
    }
    public void setModperson(String modperson) {
        this.modperson = modperson;
    }
    public Date getModtime() {
        return modtime;
    }
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getLeaf() {
        return leaf;
    }
    public void setLeaf(int leaf) {
        this.leaf = leaf;
    }
    public String getSystemid() {
        return systemid;
    }
    public void setSystemid(String systemid) {
        this.systemid = systemid;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public String getExchange() {
        return exchange;
    }
    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

}